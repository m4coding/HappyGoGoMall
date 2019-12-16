package com.m4coding.mallmanager.service;

import com.m4coding.mallbase.api.CommonPage;
import com.m4coding.mallmanager.dto.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品服务相关接口
 */
public interface PmsProductService {

    /**
     * 创建商品
     * @param pmsProductParam
     * @return
     */
    @Transactional
    int create(PmsProductParam pmsProductParam);

    /**
     * 更新商品
     * @param pmsProductUpdateParam
     * @return
     */
    @Transactional
    int update(PmsProductUpdateParam pmsProductUpdateParam);

    /**
     * 分页查询商品
     * @param pmsProductQueryParam
     * @param pageSize
     * @param pageNum
     * @return
     */
    CommonPage<ListProductResult> getProductList(PmsProductQueryParam pmsProductQueryParam, Integer pageSize, Integer pageNum);

    /**
     * 分页查询商品分类
     * @param pmsProductCategoryQueryParam
     * @param pageSize
     * @param pageNum
     * @return
     */
    CommonPage<ListProductCategoryResult> getProductCategoryList(PmsProductCategoryQueryParam pmsProductCategoryQueryParam, Integer pageSize, Integer pageNum);

    /**
     * 批量更新spu状态
     * @param ids
     * @param status
     * @return
     */
    int batchUpdateSpuStatus(List<Long> ids, Integer status);

    /**
     * 批量更新sku状态
     * @param ids
     * @param status
     * @return
     */
    int batchUpdateSkuStatus(List<Long> ids, Integer status);

}
