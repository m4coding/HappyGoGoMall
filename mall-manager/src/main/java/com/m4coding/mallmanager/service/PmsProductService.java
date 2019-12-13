package com.m4coding.mallmanager.service;

import com.m4coding.mallmanager.dto.ListProductResult;
import com.m4coding.mallmanager.dto.PmsProductParam;
import com.m4coding.mallmanager.dto.PmsProductQueryParam;
import com.m4coding.mallmanager.dto.PmsProductUpdateParam;
import com.m4coding.mallmbg.mbg.model.PmsSpu;
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
    List<ListProductResult> getList(PmsProductQueryParam pmsProductQueryParam, Integer pageSize, Integer pageNum);

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
