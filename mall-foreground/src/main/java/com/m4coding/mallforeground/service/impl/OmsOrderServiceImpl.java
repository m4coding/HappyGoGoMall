package com.m4coding.mallforeground.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.m4coding.mallforeground.dto.*;
import com.m4coding.mallforeground.service.OmsOrderService;
import com.m4coding.mallforeground.service.ShoppingCartService;
import com.m4coding.mallforeground.service.UmsUserReceiverAddressService;
import com.m4coding.mallforeground.service.UmsUserService;
import com.m4coding.mallmbg.mbg.mapper.*;
import com.m4coding.mallmbg.mbg.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 订单服务具体实现类
 */
@Service
public class OmsOrderServiceImpl implements OmsOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OmsOrderServiceImpl.class);


    @Autowired
    private PmsSpuMapper pmsSpuMapper;

    @Autowired
    private PmsSkuMapper pmsSkuMapper;

    @Autowired
    private PmsSpuSkuAttrMapper pmsSpuSkuAttrMapper;

    @Autowired
    private UmsUserReceiverAddressMapper umsUserReceiverAddressMapper;
    @Autowired
    private UmsUserService umsUserService;
    @Autowired
    private PmsSkuStockMapper pmsSkuStockMapper;
    @Autowired
    private UmsUserReceiverAddressService umsUserReceiverAddressService;
    @Autowired
    private OmsOrderMapper omsOrderMapper;
    @Autowired
    private OmsOrderItemMapper omsOrderItemMapper;
    @Autowired
    private ShoppingCartService shoppingCartService;


    @Override
    public OmsOrderConfirmResult orderConfirmInfo(OmsOrderConfirmParam omsOrderConfirmParam) {

        OmsOrderConfirmResult omsOrderConfirmResult = new OmsOrderConfirmResult();

        //商品信息
        List<OmsOrderConfirmResult.ConfirmOrderProductInfoBean> productInfoBeanList = omsOrderConfirmParam.getProductList().stream()
                .map(new Function<OmsOrderConfirmParam.OmsOrderConfirmProductParam, OmsOrderConfirmResult.ConfirmOrderProductInfoBean>() {
                    @Override
                    public OmsOrderConfirmResult.ConfirmOrderProductInfoBean apply(OmsOrderConfirmParam.OmsOrderConfirmProductParam beanParams) {

                        PmsSkuExample pmsSkuExample = new PmsSkuExample();
                        pmsSkuExample.createCriteria().andIdEqualTo(beanParams.getProductSkuId());
                        List<PmsSku> skuList = pmsSkuMapper.selectByExampleWithBLOBs(pmsSkuExample);

                        if (CollectionUtil.isEmpty(skuList)) {
                            throw new RuntimeException("找不到对应的skuid=" + beanParams.getProductSkuId());
                        }

                        PmsSku pmsSku =  skuList.get(0);

                        PmsSpuExample pmsSpuExample = new PmsSpuExample();
                        PmsSpuExample.Criteria criteria = pmsSpuExample.createCriteria();
                        criteria.andProductIdEqualTo(pmsSku.getSpuId());

                        List<PmsSpu> spuList = pmsSpuMapper.selectByExampleWithBLOBs(pmsSpuExample);

                        if (CollectionUtil.isEmpty(spuList)) {
                            throw new RuntimeException("找不到对应的spuid=" + pmsSku.getSpuId());
                        }

                        PmsSpu pmsSpu = spuList.get(0);

                        OmsOrderConfirmResult.ConfirmOrderProductInfoBean productInfoBean = new OmsOrderConfirmResult.ConfirmOrderProductInfoBean();

                        //商品数量
                        productInfoBean.setQuantity(beanParams.getQuantity());

                        //市场价、销售价
                        productInfoBean.setProductOrgPrice(pmsSku.getMarketPrice() + "");
                        productInfoBean.setProductPrice(pmsSku.getSalePrice() + "");

                        //商品图
                        List<String> urlList = StrUtil.splitTrim(pmsSku.getBannerUrl(), ",");
                        if (CollectionUtil.isNotEmpty(urlList)) {
                            productInfoBean.setImageUrl(urlList.get(0));
                        }

                        //商品名称, 拼接规格参数
                        StringBuilder productNameBuilder = new StringBuilder(pmsSpu.getProductName());
                        PmsSpuSkuAttrExample pmsSpuSkuAttrExample = new PmsSpuSkuAttrExample();
                        pmsSpuSkuAttrExample.createCriteria().andSkuIdEqualTo(pmsSku.getId());
                        List<PmsSpuSkuAttr> pmsSpuSkuAttrList = pmsSpuSkuAttrMapper.selectByExample(pmsSpuSkuAttrExample);
                        if (!CollectionUtil.isEmpty(pmsSpuSkuAttrList)) {
                            for (PmsSpuSkuAttr pmsSpuSkuAttr : pmsSpuSkuAttrList) {
                                productNameBuilder.append(" ")
                                        .append(pmsSpuSkuAttr.getAttrValueName());
                            }
                        }
                        productInfoBean.setProductName(productNameBuilder.toString());

                        //商品id
                        productInfoBean.setProductSkuId(pmsSku.getId());

                        return productInfoBean;
                    }
                })
                .collect(Collectors.toList());

        omsOrderConfirmResult.setProductList(productInfoBeanList);

        UmsUser umsUser = umsUserService.getCurrentUser();

        UmsUserReceiverAddressExample umsUserReceiverAddressExample = new UmsUserReceiverAddressExample();
        umsUserReceiverAddressExample.createCriteria().andUserIdEqualTo(umsUser.getUserId().longValue());
        List<UmsUserReceiverAddress> addressList = umsUserReceiverAddressMapper.selectByExample(umsUserReceiverAddressExample);

        //取默认的收货地址
        if (CollectionUtil.isNotEmpty(addressList)) {
            List<UmsUserReceiverAddress> defaultAddressList =
                    addressList.stream().filter(address -> address.getDefaultStatus() == 1).collect(Collectors.toList());

            if (CollectionUtil.isNotEmpty(defaultAddressList)) {
                omsOrderConfirmResult.setUserReceiverAddress(defaultAddressList.get(0));
            }
        }

        //计算商品总额
        omsOrderConfirmResult.setCalculateSum(calcOrderPrice(omsOrderConfirmResult.getProductList()));

        return omsOrderConfirmResult;
    }


    /**
     * 计算订单中商品的价格
     */
    private OmsOrderConfirmResult.OmsOrderConfirmCalculateSum calcOrderPrice(List<OmsOrderConfirmResult.ConfirmOrderProductInfoBean> productInfoList) {
        OmsOrderConfirmResult.OmsOrderConfirmCalculateSum calcTotalPrice = new OmsOrderConfirmResult.OmsOrderConfirmCalculateSum();
        calcTotalPrice.setFreightAmount(new BigDecimal(0)); //运费
        BigDecimal productTotalPrice = new BigDecimal("0"); //订单商品总金额
        for (OmsOrderConfirmResult.ConfirmOrderProductInfoBean productInfoBean : productInfoList) {
            productTotalPrice = productTotalPrice.add(new BigDecimal(productInfoBean.getProductPrice()).multiply(new BigDecimal(productInfoBean.getQuantity())));
        }
        calcTotalPrice.setTotalProductAmount(productTotalPrice);
        calcTotalPrice.setPayAmount(productTotalPrice.add(calcTotalPrice.getFreightAmount())); //实付金额计算
        return calcTotalPrice;
    }

    @Override
    public OmsOrderPlaceResult placeOrder(OmsOrderPlaceParam omsOrderPlaceParam) throws Exception {

        OmsOrderPlaceResult omsOrderPlaceResult = new OmsOrderPlaceResult();

        //当前的用户信息
        UmsUser currentUser = umsUserService.getCurrentUser();

        //商品信息
        List<OmsOrderPlaceParam.ProductInfoBean> productInfoBeanList = omsOrderPlaceParam.getProductList().stream()
            .map(new Function<OmsOrderPlaceParam.ProductParam, OmsOrderPlaceParam.ProductInfoBean>() {
                @Override
                public OmsOrderPlaceParam.ProductInfoBean apply(OmsOrderPlaceParam.ProductParam beanParams) {

                    PmsSkuExample pmsSkuExample = new PmsSkuExample();
                    pmsSkuExample.createCriteria().andIdEqualTo(beanParams.getProductSkuId());
                    List<PmsSku> skuList = pmsSkuMapper.selectByExampleWithBLOBs(pmsSkuExample);

                    if (CollectionUtil.isEmpty(skuList)) {
                        throw new RuntimeException("找不到对应的skuid=" + beanParams.getProductSkuId());
                    }

                    PmsSku pmsSku =  skuList.get(0);

                    PmsSpuExample pmsSpuExample = new PmsSpuExample();
                    PmsSpuExample.Criteria criteria = pmsSpuExample.createCriteria();
                    criteria.andProductIdEqualTo(pmsSku.getSpuId());

                    List<PmsSpu> spuList = pmsSpuMapper.selectByExampleWithBLOBs(pmsSpuExample);

                    if (CollectionUtil.isEmpty(spuList)) {
                        throw new RuntimeException("找不到对应的spuid=" + pmsSku.getSpuId());
                    }

                    PmsSpu pmsSpu = spuList.get(0);

                    OmsOrderPlaceParam.ProductInfoBean productInfoBean = new OmsOrderPlaceParam.ProductInfoBean();

                    //商品数量
                    productInfoBean.setQuantity(beanParams.getQuantity());

                    //市场价、销售价
                    productInfoBean.setProductOrgPrice(pmsSku.getMarketPrice());
                    productInfoBean.setProductPrice(pmsSku.getSalePrice());

                    //商品图
                    List<String> urlList = StrUtil.splitTrim(pmsSku.getBannerUrl(), ",");
                    if (CollectionUtil.isNotEmpty(urlList)) {
                        productInfoBean.setImageUrl(urlList.get(0));
                    }

                    //商品名称, 拼接规格参数
                    StringBuilder productNameBuilder = new StringBuilder(pmsSpu.getProductName());
                    PmsSpuSkuAttrExample pmsSpuSkuAttrExample = new PmsSpuSkuAttrExample();
                    pmsSpuSkuAttrExample.createCriteria().andSkuIdEqualTo(pmsSku.getId());
                    List<PmsSpuSkuAttr> pmsSpuSkuAttrList = pmsSpuSkuAttrMapper.selectByExample(pmsSpuSkuAttrExample);
                    if (!CollectionUtil.isEmpty(pmsSpuSkuAttrList)) {
                        for (PmsSpuSkuAttr pmsSpuSkuAttr : pmsSpuSkuAttrList) {
                            productNameBuilder.append(" ")
                                    .append(pmsSpuSkuAttr.getAttrValueName());
                        }
                    }
                    productInfoBean.setProductName(productNameBuilder.toString());

                    //商品id
                    productInfoBean.setProductSkuId(pmsSku.getId());

                    //商品库存
                    PmsSkuStockExample pmsSkuStockExample = new PmsSkuStockExample();
                    pmsSkuStockExample.createCriteria().andSkuIdEqualTo(pmsSku.getId().intValue());
                    List<PmsSkuStock> stockList = pmsSkuStockMapper.selectByExample(pmsSkuStockExample);
                    if (CollectionUtil.isNotEmpty(stockList)) {
                        PmsSkuStock pmsSkuStock = stockList.get(0);
                        if (pmsSkuStock.getStatus() == 1) {
                            productInfoBean.setRealStock((long) (pmsSkuStock.getQuantity() - pmsSkuStock.getLockStock()));
                        }
                    }

                    //库存检查
                    if (beanParams.getQuantity() > productInfoBean.getRealStock()) {
                        LOGGER.error("下单异常：" + "productSkuId=" + productInfoBean.getProductSkuId() + "商品库存不足");
                        throw new RuntimeException("库存不足，无法下单");
                    }

                    //订单item表相关的
                    productInfoBean.setProductQuantity(productInfoBean.getQuantity().intValue());
                    productInfoBean.setProductId(pmsSku.getSpuId()); //spuId
                    productInfoBean.setProductPrice(productInfoBean.getProductPrice());

                    return productInfoBean;
                }
            })
            .collect(Collectors.toList());

        OmsOrder order = new OmsOrder();
        order.setTotalAmount(calcTotalAmount(productInfoBeanList));
        //锁定库存
        lockStock(productInfoBeanList);
        //计算实付金额
        calcPayAmount(order);
        order.setMemberId(currentUser.getUserId().longValue());
        order.setCreateTime((int) (System.currentTimeMillis() / 1000));
        order.setMemberUsername(currentUser.getUserName());
        //支付方式：0->未支付；1->支付宝；2->微信
        order.setPayType(0);
        //订单来源：0->PC订单；1->app订单
        order.setSourceType(1);
        //订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单
        order.setStatus(0);
        //订单类型：0->正常订单；1->秒杀订单
        order.setOrderType(0);
        //收货人信息：姓名、电话、邮编、地址
        UmsUserReceiverAddress address = umsUserReceiverAddressService.getItem(omsOrderPlaceParam.getReceiveAddressId());
        if (address == null) {
            LOGGER.error("umsUserReceiverAddressService.getItem is null");
            throw new Exception("下单失败，缺少收货地址");
        }
        order.setReceiverName(address.getName());
        order.setReceiverPhone(address.getPhoneNumber());
        order.setReceiverPostCode(address.getPostCode());
        order.setReceiverProvince(address.getProvince());
        order.setReceiverCity(address.getCity());
        order.setReceiverRegion(address.getRegion());
        order.setReceiverDetailAddress(address.getDetailAddress());
        //0->未确认；1->已确认
        order.setConfirmStatus(0);
        //订单删除状态
        order.setDeleteStatus(0);
        //生成订单号
        order.setOrderSn(generateOrderSn(order));
        if (omsOrderMapper.insert(order) <= 0) {
            LOGGER.error("omsOrderMapper.insert error {}", order.getOrderSn());
            throw new Exception("下单失败");
        }

        //插入oms order item表
        for (OmsOrderItem orderItem : productInfoBeanList) {
            orderItem.setOrderId(order.getId());
            orderItem.setOrderSn(order.getOrderSn());

            if (omsOrderItemMapper.insertSelective(orderItem) <= 0) {
                LOGGER.error("omsOrderItemMapper.insertSelective error {}", orderItem.getOrderId());
                throw new Exception("下单失败");
            }
        }

        //删除购物车中的下单商品
        deleteCartItemList(productInfoBeanList);

        return omsOrderPlaceResult;
    }

    //计算商品总金额
    private BigDecimal calcTotalAmount(List<OmsOrderPlaceParam.ProductInfoBean> productInfoBeanList) {
        BigDecimal totalAmount = new BigDecimal("0");
        for (OmsOrderPlaceParam.ProductInfoBean item : productInfoBeanList) {
            totalAmount = totalAmount.add(item.getProductPrice()).multiply(new BigDecimal(item.getQuantity()));
        }
        return totalAmount;
    }

    //计算订单应付金额
    private BigDecimal calcPayAmount(OmsOrder order) {
        //总金额+运费-促销优惠-优惠券优惠-积分抵扣
        BigDecimal payAmount = order.getTotalAmount();
        return payAmount;
    }

    //锁定库存
    private void lockStock(List<OmsOrderPlaceParam.ProductInfoBean> productInfoBeanList) {
        for (OmsOrderPlaceParam.ProductInfoBean item : productInfoBeanList) {
            PmsSkuStockExample pmsSkuStockExample = new PmsSkuStockExample();
            pmsSkuStockExample.createCriteria().andSkuIdEqualTo(item.getProductSkuId().intValue());
            List<PmsSkuStock> stockList = pmsSkuStockMapper.selectByExample(pmsSkuStockExample);
            if (CollectionUtil.isNotEmpty(stockList)) {
                PmsSkuStock pmsSkuStock = stockList.get(0);
                pmsSkuStock.setLockStock(pmsSkuStock.getLockStock() + item.getQuantity().intValue());
                pmsSkuStockMapper.updateByPrimaryKeySelective(pmsSkuStock);
            }
        }
    }

    //年月日时分秒+随机码(2)+2位平台号码+2位支付方式+随机码(2)
    private String generateOrderSn(OmsOrder order) {
        StringBuilder sb = new StringBuilder();
        String date = new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date());
        sb.append(date);
        sb.append(generatorTowRandom());
        sb.append(String.format("%02d", order.getSourceType()));
        sb.append(String.format("%02d", order.getPayType()));
        sb.append(generatorTowRandom());
        return sb.toString();
    }

    //生成两位随机数
    private  String generatorTowRandom() {
        Random random = new Random();
        String result = String.valueOf(random.nextInt(100));
        if (result.length() == 1) {
            result = "0" + result;
        }
        return result;
    }

    //删除下单商品的购物车信息
    private void deleteCartItemList(List<OmsOrderPlaceParam.ProductInfoBean> productInfoBeanList) throws Exception {
        List<Long> skuIds = new ArrayList<>();
        for (OmsOrderPlaceParam.ProductInfoBean productInfoBean : productInfoBeanList) {
            skuIds.add(productInfoBean.getProductSkuId());
        }

        OmsCartDeleteParam omsCartDeleteParam = new OmsCartDeleteParam();
        omsCartDeleteParam.setProductSkuIds(skuIds);
        if (!shoppingCartService.deleteInCart(omsCartDeleteParam)) {
            LOGGER.error("shoppingCartService.deleteInCart error {}", omsCartDeleteParam.getProductSkuIds());
            throw new Exception("下单失败");
        }
    }

}
