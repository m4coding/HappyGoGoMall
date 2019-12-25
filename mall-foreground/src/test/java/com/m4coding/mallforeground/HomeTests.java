package com.m4coding.mallforeground;

import com.m4coding.mallforeground.dto.HomeProductCategoryQueryParam;
import com.m4coding.mallforeground.dto.HomeProductListQueryParam;
import com.m4coding.mallforeground.utils.BusinessTestUtils;
import org.junit.Test;

public class HomeTests {

    /**
     * 获取首页信息列表
     */
    @Test
    public void getHomeContentInfo() {
        if (!BusinessTestUtils.isLogin()) {
            BusinessTestUtils.login();
        }

        BusinessTestUtils.postAuth(BusinessTestUtils.HTTP_DOMAIN + "/api/home/v1/pageContentInfo", "");
    }

    /**
     * 获取首页商品列表
     */
    @Test
    public void getHomeProductList() {
        if (!BusinessTestUtils.isLogin()) {
            BusinessTestUtils.login();
        }

        HomeProductListQueryParam homeProductListQueryParam = new HomeProductListQueryParam();
        homeProductListQueryParam.setPageNum(1);
        homeProductListQueryParam.setPageSize(10);

        BusinessTestUtils.postAuth(BusinessTestUtils.HTTP_DOMAIN + "/api/home/v1/pageListInfo", homeProductListQueryParam);
    }

    /**
     * 获取商品分类列表
     */
    @Test
    public void getProductCategoryList() {
        if (!BusinessTestUtils.isLogin()) {
            BusinessTestUtils.login();
        }

        HomeProductCategoryQueryParam homeProductCategoryQueryParam = new HomeProductCategoryQueryParam();
        homeProductCategoryQueryParam.setKeyword("");
        homeProductCategoryQueryParam.setPageNum(1);
        homeProductCategoryQueryParam.setPageSize(5);

        BusinessTestUtils.postAuth(BusinessTestUtils.HTTP_DOMAIN + "/api/home/v1/getCategoryList", homeProductCategoryQueryParam);
    }
}
