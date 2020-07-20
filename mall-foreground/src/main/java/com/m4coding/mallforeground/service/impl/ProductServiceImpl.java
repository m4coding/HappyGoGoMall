package com.m4coding.mallforeground.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.m4coding.mallforeground.dto.product.ProductDetailsParam;
import com.m4coding.mallforeground.dto.product.ProductDetailsResult;
import com.m4coding.mallforeground.service.ProductService;
import com.m4coding.mallmbg.mbg.mapper.*;
import com.m4coding.mallmbg.mbg.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品服务具体实现类
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private PmsSpuMapper pmsSpuMapper;

    @Autowired
    private PmsSkuMapper pmsSkuMapper;

    @Autowired
    private PmsSpuSkuAttrMapper pmsSpuSkuAttrMapper;

    @Autowired
    private PmsCategoryMapper pmsCategoryMapper;

    @Autowired
    private PmsBrandMapper pmsBrandMapper;

    @Autowired
    private PmsSkuStockMapper pmsSkuStockMapper;

    @Override
    public ProductDetailsResult getProductDetails(ProductDetailsParam productDetailsParam) throws Exception {


        PmsSpuExample pmsSpuExample = new PmsSpuExample();
        PmsSpuExample.Criteria criteria = pmsSpuExample.createCriteria();
        criteria.andProductIdEqualTo(productDetailsParam.getProductSkuId().longValue());

        List<PmsSpu> spuList = pmsSpuMapper.selectByExampleWithBLOBs(pmsSpuExample);
        if (CollectionUtil.isEmpty(spuList)) {
            throw new Exception("找不到对应的spu");
        } else {

            PmsSpu pmsSpu = spuList.get(0); //spu

            PmsSkuExample pmsSkuExample = new PmsSkuExample();
            pmsSkuExample.createCriteria().andSpuIdEqualTo(pmsSpu.getProductId());
            List<PmsSku> skuList = pmsSkuMapper.selectByExampleWithBLOBs(pmsSkuExample);

            if (CollectionUtil.isEmpty(skuList)) {
                throw new Exception("找不到对应的sku");
            } else {

                PmsSku pmsSku = skuList.get(0); //sku

                ProductDetailsResult productDetailsResult = new ProductDetailsResult();

                //市场价、销售价
                productDetailsResult.setProductOrgPrice(pmsSku.getMarketPrice() + "");
                productDetailsResult.setProductPrice(pmsSku.getSalePrice() + "");

                //商品图
                List<String> urlList = StrUtil.splitTrim(pmsSku.getBannerUrl(), ",");
                productDetailsResult.setImageUrls(urlList);

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
                productDetailsResult.setProductName(productNameBuilder.toString());

                //商品id
                productDetailsResult.setProductSkuId(pmsSku.getId());
                //spu Id
                productDetailsResult.setProductSpuId(pmsSpu.getProductId());

                //分类名称
                PmsCategory pmsCategory = pmsCategoryMapper.selectByPrimaryKey(pmsSpu.getCategoryId());
                productDetailsResult.setCategory(pmsCategory.getCategoryName());

                //品牌名称
                PmsBrand pmsBrand = pmsBrandMapper.selectByPrimaryKey(pmsSpu.getBrandId());
                if (pmsBrand != null) {
                    productDetailsResult.setBrand(pmsBrand.getBrandName());
                }

                //库存
                PmsSkuStockExample pmsSkuStockExample = new PmsSkuStockExample();
                pmsSkuStockExample.createCriteria().andSkuIdEqualTo(pmsSku.getId().intValue());
                List<PmsSkuStock> pmsSkuStockList =  pmsSkuStockMapper.selectByExample(pmsSkuStockExample);
                if (CollectionUtil.isNotEmpty(pmsSkuStockList)) {
                    productDetailsResult.setStock(pmsSkuStockList.get(0).getQuantity());
                }

                return productDetailsResult;
            }
        }
    }
}
