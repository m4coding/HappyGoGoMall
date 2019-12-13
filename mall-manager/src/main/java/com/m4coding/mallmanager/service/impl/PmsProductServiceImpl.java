package com.m4coding.mallmanager.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.m4coding.mallmanager.dto.ListProductResult;
import com.m4coding.mallmanager.dto.PmsProductParam;
import com.m4coding.mallmanager.dto.PmsProductQueryParam;
import com.m4coding.mallmanager.dto.PmsProductUpdateParam;
import com.m4coding.mallmanager.service.PmsProductService;
import com.m4coding.mallmbg.mbg.mapper.*;
import com.m4coding.mallmbg.mbg.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品中心服务
 */
@Service
public class PmsProductServiceImpl implements PmsProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsProductServiceImpl.class);

    @Autowired
    private PmsSpuMapper pmsSpuMapper;
    @Autowired
    private PmsSkuMapper pmsSkuMapper;
    @Autowired
    private PmsSkuAttrMapper pmsSkuAttrMapper;
    @Autowired
    private PmsSkuAttrValueMapper pmsSkuAttrValueMapper;
    @Autowired
    private PmsSkuStockMapper pmsSkuStockMapper;
    @Autowired
    private PmsSpuSkuAttrMapper pmsSpuSkuAttrMapper;
    @Autowired
    private PmsBrandMapper pmsBrandMapper;
    @Autowired
    private PmsCategoryMapper pmsCategoryMapper;


    @Override
    public int create(PmsProductParam pmsProductParam) {

        PmsSpuExample pmsSpuExample = new PmsSpuExample();
        pmsSpuExample.createCriteria().andProductNameEqualTo(pmsProductParam.getProductName());
        if (!CollectionUtil.isEmpty(pmsSpuMapper.selectByExample(pmsSpuExample))) {
            LOGGER.error("商品创建异常：商品已存在");
            throw new RuntimeException("商品已存在");
        }

        //spu
        PmsSpu pmsSpu = new PmsSpu();
        pmsSpu.setCategoryId(pmsProductParam.getCategoryId());
        pmsSpu.setBrandId(pmsProductParam.getBrandId());
        pmsSpu.setProductName(pmsProductParam.getProductName());
        pmsSpu.setDescription(pmsProductParam.getDescription());
        pmsSpu.setMainUrl(CollectionUtil.join(pmsProductParam.getMainPicList(), ","));
        pmsSpu.setBannerUrl(CollectionUtil.join(pmsProductParam.getBannerPicList(), ","));
        pmsSpu.setCreateTime((int) DateUtil.currentSeconds());
        pmsSpu.setUpdateTime((int) DateUtil.currentSeconds());
        pmsSpu.setStatus(1);

        if (pmsSpuMapper.insertSelective(pmsSpu) != 1) {
            LOGGER.error("商品创建异常：spu表插入错误");
            throw new RuntimeException("商品创建错误");
        }

        //属性相关
        StringBuilder attrValueId = new StringBuilder();
        List<PmsSpuSkuAttr> skuAttrValueList = new ArrayList<>();
        for (int i = 0; i < pmsProductParam.getAttrs().size(); i++) {
            PmsProductParam.Attrs attr = pmsProductParam.getAttrs().get(i);


            PmsSkuAttrExample pmsSkuAttrExample = new PmsSkuAttrExample();
            pmsSkuAttrExample.createCriteria().andNameEqualTo(attr.getAttrsKey());
            List<PmsSkuAttr> list =  pmsSkuAttrMapper.selectByExample(pmsSkuAttrExample);
            if (CollectionUtil.isEmpty(list)) {
                LOGGER.error("商品创建异常：不存在此属性");
                throw new RuntimeException("商品创建错误");
            }


            PmsSkuAttrValue pmsSkuAttrValue = new PmsSkuAttrValue();
            pmsSkuAttrValue.setAttrId(list.get(0).getId()); //填充对应的属性id
            pmsSkuAttrValue.setValue(attr.getAttrValue());
            pmsSkuAttrValue.setDescription(attr.getAttrValueDescription());
            pmsSkuAttrValue.setCreateTime((int) DateUtil.currentSeconds());
            pmsSkuAttrValue.setUpdateTime((int) DateUtil.currentSeconds());
            pmsSkuAttrValue.setStatus(1);

            if (pmsSkuAttrValueMapper.insertSelective(pmsSkuAttrValue) != 1) {
                LOGGER.error("商品创建异常：属性值插入异常");
                throw new RuntimeException("商品创建错误");
            }

            //构造spu-sku-属性id-属性值id相关bean,并加入缓存list
            PmsSpuSkuAttr pmsSpuSkuAttr = new PmsSpuSkuAttr();
            pmsSpuSkuAttr.setSpuId(pmsSpu.getProductId());
            pmsSpuSkuAttr.setAttrId(list.get(0).getId());
            pmsSpuSkuAttr.setAttrName(list.get(0).getName());
            pmsSpuSkuAttr.setAttrValueId(pmsSkuAttrValue.getId());
            pmsSpuSkuAttr.setAttrValueName(pmsSkuAttrValue.getValue());
            pmsSpuSkuAttr.setCreateTime((int) DateUtil.currentSeconds());
            pmsSpuSkuAttr.setUpdateTime((int) DateUtil.currentSeconds());
            skuAttrValueList.add(pmsSpuSkuAttr);

            //构造属性值id   id,id,id
            if (0 == i) {
                attrValueId.append(pmsSkuAttrValue.getId());
            } else {
                attrValueId.append(",").append(pmsSkuAttrValue.getId());
            }

        }

        //sku插入
        PmsSku pmsSku = new PmsSku();
        pmsSku.setSalePrice(pmsProductParam.getSalePrice());
        pmsSku.setMarketPrice(pmsProductParam.getMarketPrice());
        pmsSku.setSpuId(pmsSpu.getProductId());
        pmsSku.setAttrs(attrValueId.toString());
        pmsSku.setCreateTime((int) DateUtil.currentSeconds());
        pmsSku.setUpdateTime((int) DateUtil.currentSeconds());
        pmsSku.setStatus(1);

        if (pmsSkuMapper.insertSelective(pmsSku) != 1) {
            LOGGER.error("商品创建异常：sku插入异常");
            throw new RuntimeException("商品创建错误");
        }

        //库存相关
        PmsSkuStock pmsSkuStock = new PmsSkuStock();
        pmsSkuStock.setQuantity(pmsProductParam.getStock().intValue());
        pmsSkuStock.setSkuId(pmsSku.getId().intValue());
        pmsSkuStock.setStatus(1);
        if (pmsSkuStockMapper.insertSelective(pmsSkuStock) != 1) {
            LOGGER.error("商品创建异常：库存插入异常");
            throw new RuntimeException("商品创建错误");
        }

        //插入spu_sku_attr关联表
        for (PmsSpuSkuAttr pmsSpuSkuAttr : skuAttrValueList) {
            pmsSpuSkuAttr.setSkuId(pmsSku.getId());
            pmsSpuSkuAttr.setStatus(1);
            if (pmsSpuSkuAttrMapper.insertSelective(pmsSpuSkuAttr) != 1) {
                LOGGER.error("商品创建异常：Spu-Sku-Attr关联插入异常");
                throw new RuntimeException("商品创建错误");
            }
        }

        return 1;
    }

    @Override
    public int update(PmsProductUpdateParam pmsProductUpdateParam) {
        //更新spu
        PmsSpu pmsSpu = new PmsSpu();
        pmsSpu.setProductId(pmsProductUpdateParam.getProductId());
        pmsSpu.setUpdateTime((int) DateUtil.currentSeconds());
        pmsSpu.setBannerUrl(CollectionUtil.join(pmsProductUpdateParam.getBannerPicList(), ","));
        pmsSpu.setMainUrl(CollectionUtil.join(pmsProductUpdateParam.getMainPicList(), ","));
        pmsSpu.setDescription(pmsProductUpdateParam.getDescription());
        pmsSpu.setProductName(pmsProductUpdateParam.getProductName());
        pmsSpu.setBrandId(pmsProductUpdateParam.getBrandId());
        pmsSpu.setCategoryId(pmsProductUpdateParam.getCategoryId());

        if (pmsSpuMapper.updateByPrimaryKeySelective(pmsSpu) != 1) {
            LOGGER.error("商品更新异常：Spu更新异常");
            throw new RuntimeException("商品更新异常");
        }

        //更新sku
        if (pmsProductUpdateParam.getSkuId() != null) {
            PmsSku pmsSku = new PmsSku();
            pmsSku.setId(pmsProductUpdateParam.getSkuId());
            pmsSku.setSpuId(pmsProductUpdateParam.getProductId());
            pmsSku.setMarketPrice(pmsProductUpdateParam.getMarketPrice());
            pmsSku.setSalePrice(pmsProductUpdateParam.getSalePrice());
            pmsSku.setUpdateTime((int) DateUtil.currentSeconds());
            StringBuilder attrValueIdSet = new StringBuilder();
            for (int i = 0; i < pmsProductUpdateParam.getAttrs().size(); i++) {
                if (i == 0) {
                    attrValueIdSet.append(pmsProductUpdateParam.getAttrs().get(0).getAttrValueId());
                } else {
                    attrValueIdSet.append(",").append(pmsProductUpdateParam.getAttrs().get(0).getAttrValueId());
                }

                PmsProductParam.Attrs paramAttr = pmsProductUpdateParam.getAttrs().get(i);

                PmsSpuSkuAttr pmsSpuSkuAttr = new PmsSpuSkuAttr();
                PmsSpuSkuAttrExample pmsSpuSkuAttrExample = new PmsSpuSkuAttrExample();
                pmsSpuSkuAttrExample.createCriteria().andSpuIdEqualTo(pmsProductUpdateParam.getProductId())
                        .andSkuIdEqualTo(pmsProductUpdateParam.getSkuId());

                pmsSpuSkuAttr.setAttrId(paramAttr.getAttrKeyId());
                pmsSpuSkuAttr.setAttrName(paramAttr.getAttrsKey());
                pmsSpuSkuAttr.setAttrValueId(paramAttr.getAttrValueId());
                pmsSpuSkuAttr.setAttrValueName(paramAttr.getAttrValue());
                pmsSpuSkuAttr.setUpdateTime((int) DateUtil.currentSeconds());

                if (pmsSpuSkuAttrMapper.updateByExampleSelective(pmsSpuSkuAttr, pmsSpuSkuAttrExample) != 1) {
                    LOGGER.error("商品更新异常：PmsSpuSkuAttr更新异常");
                    throw new RuntimeException("商品更新异常");
                }
            }
            pmsSku.setAttrs(attrValueIdSet.toString());

            if (pmsSkuMapper.updateByPrimaryKeySelective(pmsSku) != 1) {
                LOGGER.error("商品更新异常：sku更新异常");
                throw new RuntimeException("商品更新异常");
            }

            //更新库存
            PmsSkuStock pmsSkuStock = new PmsSkuStock();
            pmsSkuStock.setSkuId(pmsSku.getId().intValue());
            pmsSkuStock.setQuantity(pmsProductUpdateParam.getStock().intValue());
            pmsSkuStock.setUpdateTime((int) DateUtil.currentSeconds());
            PmsSkuStockExample pmsSkuStockExample = new PmsSkuStockExample();
            pmsSkuStockExample.createCriteria().andSkuIdEqualTo(pmsSkuStock.getSkuId());
            if (pmsSkuStockMapper.updateByExampleSelective(pmsSkuStock, pmsSkuStockExample) != 1) {
                LOGGER.error("商品更新异常：sku库存表更新异常");
                throw new RuntimeException("商品更新异常");
            }
        }

        return 1;
    }

    @Override
    public List<ListProductResult> getList(PmsProductQueryParam pmsProductQueryParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);

        List<ListProductResult> resultList = new ArrayList<>();

        PmsSpuExample pmsSpuExample = new PmsSpuExample();
        PmsSpuExample.Criteria criteria = pmsSpuExample.createCriteria();

        //名称模糊搜索
        if (!StrUtil.isEmpty(pmsProductQueryParam.getKeyword())) {
            criteria.andProductNameLike("%" + pmsProductQueryParam.getKeyword() + "%");
            List<PmsSpu> spuList = pmsSpuMapper.selectByExample(pmsSpuExample);
            for (PmsSpu pmsSpu : spuList) {
                PmsSkuExample pmsSkuExample = new PmsSkuExample();
                pmsSkuExample.createCriteria().andSpuIdEqualTo(pmsSpu.getProductId());
                List<PmsSku> skuList = pmsSkuMapper.selectByExample(pmsSkuExample);
                for (PmsSku pmsSku : skuList) {

                    ListProductResult listProductResult = new ListProductResult();

                    //名牌名
                    PmsBrandExample pmsBrandExample = new PmsBrandExample();
                    pmsBrandExample.createCriteria().andIdEqualTo(pmsSpu.getBrandId());
                    List<PmsBrand> pmsBrandList = pmsBrandMapper.selectByExample(pmsBrandExample);
                    if (!CollectionUtil.isEmpty(pmsBrandList)) {
                        listProductResult.setBrandName(pmsBrandList.get(0).getBrandName());
                    }

                    //分类名称
                    PmsCategoryExample pmsCategoryExample = new PmsCategoryExample();
                    pmsCategoryExample.createCriteria().andIdEqualTo(pmsSpu.getCategoryId());
                    List<PmsCategory> pmsCategoryList = pmsCategoryMapper.selectByExample(pmsCategoryExample);
                    if (!CollectionUtil.isEmpty(pmsCategoryList)) {
                        listProductResult.setCategoryName(pmsCategoryList.get(0).getCategoryName());
                    }

                    //库存量
                    PmsSkuStockExample pmsSkuStockExample = new PmsSkuStockExample();
                    pmsSkuStockExample.createCriteria().andSkuIdEqualTo(pmsSku.getId().intValue());
                    List<PmsSkuStock> stockList = pmsSkuStockMapper.selectByExample(pmsSkuStockExample);
                    if (!CollectionUtil.isEmpty(stockList)) {
                        listProductResult.setStock(stockList.get(0).getQuantity().longValue());
                    }

                    //市场价、销售价
                    listProductResult.setMarketPrice(pmsSku.getMarketPrice());
                    listProductResult.setSalePrice(pmsSku.getSalePrice());

                    //商品图
                    listProductResult.setBannerPicList(StrUtil.splitTrim(pmsSku.getBannerUrl(), ","));

                    //商品名称
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
                    listProductResult.setName(productNameBuilder.toString());

                    //加入list
                    resultList.add(listProductResult);
                }
            }
        }

        return resultList;
    }

    @Override
    public int batchUpdateSpuStatus(List<Long> ids, Integer status) {
        PmsSpu pmsSpu = new PmsSpu();
        pmsSpu.setStatus(status);
        PmsSpuExample pmsSpuExample = new PmsSpuExample();
        pmsSpuExample.createCriteria().andProductIdIn(ids);
        return pmsSpuMapper.updateByExampleSelective(pmsSpu, pmsSpuExample);
    }

    @Override
    public int batchUpdateSkuStatus(List<Long> ids, Integer status) {
        PmsSku pmsSku = new PmsSku();
        pmsSku.setStatus(status);
        PmsSkuExample pmsSkuExample = new PmsSkuExample();
        pmsSkuExample.createCriteria().andIdIn(ids);
        return  pmsSkuMapper.updateByExampleSelective(pmsSku, pmsSkuExample);
    }

}
