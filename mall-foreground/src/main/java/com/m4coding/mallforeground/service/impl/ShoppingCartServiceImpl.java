package com.m4coding.mallforeground.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.m4coding.mallforeground.dto.OmsCartAddParam;
import com.m4coding.mallforeground.dto.OmsCartDeleteParam;
import com.m4coding.mallforeground.dto.OmsCartInfoResult;
import com.m4coding.mallforeground.dto.OmsCartUpdateParam;
import com.m4coding.mallforeground.service.ShoppingCartService;
import com.m4coding.mallforeground.service.UmsUserService;
import com.m4coding.mallmbg.mbg.mapper.OmsCartItemMapper;
import com.m4coding.mallmbg.mbg.mapper.PmsSkuMapper;
import com.m4coding.mallmbg.mbg.mapper.PmsSpuMapper;
import com.m4coding.mallmbg.mbg.mapper.PmsSpuSkuAttrMapper;
import com.m4coding.mallmbg.mbg.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 购物车服务具体实现类
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);

    @Autowired
    private OmsCartItemMapper omsCartItemMapper;
    @Autowired
    private PmsSkuMapper pmsSkuMapper;
    @Autowired
    private PmsSpuMapper pmsSpuMapper;
    @Autowired
    private PmsSpuSkuAttrMapper pmsSpuSkuAttrMapper;
    @Autowired
    private UmsUserService umsUserService;

    @Override
    public boolean addCart(OmsCartAddParam omsCartAddParam) throws Exception {

        PmsSkuExample pmsSkuExample = new PmsSkuExample();
        pmsSkuExample.createCriteria().andIdEqualTo(omsCartAddParam.getProductSkuId());
        List<PmsSku> skuList = pmsSkuMapper.selectByExample(pmsSkuExample);

        if (CollectionUtil.isEmpty(skuList)) {
            LOGGER.error("添加购物车异常：pmsSkuMapper.selectByExample 找不到对应商品 {}", omsCartAddParam.getProductSkuId());
            throw new Exception("找不到对应商品");
        }

        PmsSku pmsSku = skuList.get(0);

        PmsSpuExample pmsSpuExample = new PmsSpuExample();
        pmsSpuExample.createCriteria().andProductIdEqualTo(pmsSku.getSpuId());
        List<PmsSpu> spuList = pmsSpuMapper.selectByExample(pmsSpuExample);

        if (CollectionUtil.isEmpty(spuList)) {
            LOGGER.error("添加购物车异常：pmsSpuMapper.selectByExample 找不到对应商品 {}", omsCartAddParam.getProductSkuId());
            throw new Exception("找不到对应商品");
        }

        PmsSpu pmsSpu = spuList.get(0);

        Long userId = umsUserService.getCurrentUser().getUserId().longValue();


        int id;
        OmsCartItem omsCartItem = getCartItem(userId, omsCartAddParam.getProductSkuId());
        if (omsCartItem != null) {
            omsCartItem.setQuantity(omsCartAddParam.getQuantity());
            omsCartItem.setModifyDate((int) System.currentTimeMillis());
            id = omsCartItemMapper.updateByPrimaryKey(omsCartItem);
        } else {
            omsCartItem = new OmsCartItem();
            omsCartItem.setUserId(userId);
            omsCartItem.setDeleteStatus(0);
            omsCartItem.setItemStatus(0);
            omsCartItem.setProductSkuId(omsCartAddParam.getProductSkuId());
            omsCartItem.setQuantity(omsCartAddParam.getQuantity());
            omsCartItem.setBrandId(pmsSpu.getBrandId() + "");
            omsCartItem.setCategoryId(pmsSpu.getCategoryId() + "");
            omsCartItem.setPrice(pmsSku.getSalePrice());
            omsCartItem.setCreateDate((int) System.currentTimeMillis());
            omsCartItem.setModifyDate((int) System.currentTimeMillis());
            id = omsCartItemMapper.insert(omsCartItem);
        }

        if (id <= 0) {
            LOGGER.error("添加购物车异常：omsCartItemMapper.insert {}", omsCartItem.getProductSkuId());
            throw new Exception("加入购物车失败");
        }

        return true;
    }

    /**
     * 根据用户id,商品id购物车中商品
     */
    private OmsCartItem getCartItem(Long userId, Long productSkuId) {
        OmsCartItemExample example = new OmsCartItemExample();
        OmsCartItemExample.Criteria criteria = example.createCriteria().andUserIdEqualTo(userId)
                .andProductSkuIdEqualTo(productSkuId).andDeleteStatusEqualTo(0);

        List<OmsCartItem> cartItemList = omsCartItemMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(cartItemList)) {
            return cartItemList.get(0);
        }
        return null;
    }

    @Override
    public boolean deleteInCart(OmsCartDeleteParam omsCartDeleteParam) {
        if (CollectionUtil.isEmpty(omsCartDeleteParam.getProductSkuIds())) {
            LOGGER.error("购物车删除商品异常：omsCartDeleteParam.getProductSkuIds() isEmpty");
            return false;
        }

        OmsCartItemExample omsCartItemExample = new OmsCartItemExample();
        omsCartItemExample.createCriteria().andProductSkuIdIn(omsCartDeleteParam.getProductSkuIds());

        return omsCartItemMapper.deleteByExample(omsCartItemExample) > 0;
    }

    @Override
    public OmsCartInfoResult getCartInfo() throws Exception {

        UmsUser umsUser = umsUserService.getCurrentUser();

        if (umsUser == null) {
            throw new Exception("找不到当前用户");
        }

        OmsCartInfoResult omsCartInfoResult = new OmsCartInfoResult();
        List<OmsCartInfoResult.CartProductInfoBean> productInfoBeanList = new ArrayList<>();
        omsCartInfoResult.setProductList(productInfoBeanList);

        OmsCartItemExample omsCartItemExample = new OmsCartItemExample();
        omsCartItemExample.createCriteria().andUserIdEqualTo(umsUser.getUserId().longValue());
        List<OmsCartItem> list = omsCartItemMapper.selectByExample(omsCartItemExample);

        for (OmsCartItem omsCartItem : list) {
            OmsCartInfoResult.CartProductInfoBean cartProductInfoBean = new OmsCartInfoResult.CartProductInfoBean();
            cartProductInfoBean.setBrandId(omsCartItem.getBrandId());
            cartProductInfoBean.setCategoryId(omsCartItem.getCategoryId());
            cartProductInfoBean.setItemStatus(omsCartItem.getItemStatus());
            cartProductInfoBean.setProductSkuId(omsCartItem.getProductSkuId());
            productInfoBeanList.add(cartProductInfoBean);
        }

        List<Long> productSkuIds = list.stream().map(OmsCartItem::getProductSkuId).collect(Collectors.toList());

        PmsSkuExample pmsSkuExample = new PmsSkuExample();
        pmsSkuExample.createCriteria().andIdIn(productSkuIds);
        List<PmsSku> skuList = pmsSkuMapper.selectByExample(pmsSkuExample);

        List<Long> productSpuIds = skuList.stream().map(PmsSku::getSpuId).collect(Collectors.toList());

        PmsSpuExample pmsSpuExample = new PmsSpuExample();
        pmsSpuExample.createCriteria().andProductIdIn(productSpuIds);
        List<PmsSpu> spuList = pmsSpuMapper.selectByExample(pmsSpuExample);

        int i = 0;
        for (PmsSpu pmsSpu : spuList) {
            int j = 0;
            for (PmsSku pmsSku : skuList) {
                if (pmsSku.getSpuId().equals(pmsSpu.getProductId())) {
                    OmsCartInfoResult.CartProductInfoBean cartProductInfoBean = productInfoBeanList.get(j);
                    if (!StringUtils.isEmpty(pmsSku.getMainUrl())) {
                        String[] mainUrls = pmsSku.getMainUrl().split(",");
                        if (mainUrls.length > 0) {
                            cartProductInfoBean.setImageUrl(mainUrls[0]);
                        }
                    }
                    cartProductInfoBean.setProductOrgPrice(pmsSku.getMarketPrice() + "");
                    cartProductInfoBean.setProductPrice(pmsSku.getSalePrice() + "");

                    //商品名称
                    StringBuilder productNameBuilder = new StringBuilder(pmsSpu.getProductName()); //先取spu的商品名称
                    PmsSpuSkuAttrExample pmsSpuSkuAttrExample = new PmsSpuSkuAttrExample();
                    pmsSpuSkuAttrExample.createCriteria().andSkuIdEqualTo(pmsSku.getId());
                    List<PmsSpuSkuAttr> pmsSpuSkuAttrList = pmsSpuSkuAttrMapper.selectByExample(pmsSpuSkuAttrExample);
                    if (!CollectionUtil.isEmpty(pmsSpuSkuAttrList)) {
                        for (PmsSpuSkuAttr pmsSpuSkuAttr : pmsSpuSkuAttrList) {
                            productNameBuilder.append(" ")
                                    .append(pmsSpuSkuAttr.getAttrValueName());
                        }
                    }
                    cartProductInfoBean.setProductName(productNameBuilder.toString());
                }
                j++;
            }

            i++;
        }

        return omsCartInfoResult;
    }

    @Override
    public boolean updateCartInfo(OmsCartUpdateParam omsCartUpdateParam) throws Exception {

        UmsUser umsUser = umsUserService.getCurrentUser();

        if (umsUser == null) {
            throw new Exception("找不到当前用户");
        }

        for (OmsCartUpdateParam.OmsCartUpdateChildParam omsCartUpdateChildParam : omsCartUpdateParam.getProductList()) {
            OmsCartItem omsCartItem = new OmsCartItem();
            omsCartItem.setQuantity(omsCartUpdateChildParam.getQuantity());
            omsCartItem.setItemStatus(omsCartUpdateChildParam.getItemStatus());

            OmsCartItemExample omsCartItemExample = new OmsCartItemExample();
            omsCartItemExample.createCriteria()
                    .andUserIdEqualTo(umsUser.getUserId().longValue())
                    .andProductSkuIdEqualTo(omsCartUpdateChildParam.getProductSkuId());

            int id = omsCartItemMapper.updateByExampleSelective(omsCartItem, omsCartItemExample);
            if (id <= 0) {
                throw new Exception("找不到对应的更新商品");
            }
        }

        return true;
    }

    @Override
    public Integer getCartCount() throws Exception {

        UmsUser umsUser = umsUserService.getCurrentUser();

        if (umsUser == null) {
            throw new Exception("找不到当前用户");
        }


        OmsCartItemExample omsCartItemExample = new OmsCartItemExample();
        omsCartItemExample.createCriteria().andUserIdEqualTo(umsUser.getUserId().longValue());
        List<OmsCartItem> list = omsCartItemMapper.selectByExample(omsCartItemExample);

        return CollectionUtil.isEmpty(list) ? 0 : list.size();
    }
}
