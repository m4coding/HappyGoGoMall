package com.m4coding.mallforeground.service;

import com.m4coding.mallbase.api.CommonPage;
import com.m4coding.mallforeground.dto.*;


/**
 * 首页相关的服务
 */
public interface HomePageService {
    /**
     * 获取首页顶部数据
     */
    HomePageInfoResult pageContentInfo();

    /**
     * 首页列表信息  （分页）
     * @return
     */
    CommonPage<HomeCommonItemResult> pageListInfo(HomeProductListQueryParam homeProductListQueryParam);

    /**
     * 分页查询商品分类
     * @param homeProductCategoryQueryParam
     * @param pageSize
     * @param pageNum
     * @return
     */
    CommonPage<HomeProductCategoryResult> getProductCategoryList(HomeProductCategoryQueryParam homeProductCategoryQueryParam, Integer pageSize, Integer pageNum);
}
