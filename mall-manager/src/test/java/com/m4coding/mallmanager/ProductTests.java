package com.m4coding.mallmanager;

import com.m4coding.mallmanager.dto.PmsProductParam;
import com.m4coding.mallmanager.dto.PmsProductQueryParam;
import com.m4coding.mallmanager.utils.BusinessTestUtils;
import org.junit.Test;

public class ProductTests {

    /**
     * 商品创建测试
     */
    @Test
    public void productCreate() {
        if (!BusinessTestUtils.isLogin()) {
            BusinessTestUtils.login();
        }

        PmsProductParam pmsProductParam = new PmsProductParam();
        pmsProductParam.setBrandId((long) 2);
        pmsProductParam.setProductName("测试商品");

        BusinessTestUtils.postAuth(BusinessTestUtils.HTTP_DOMAIN + "/api/product/v1/create", pmsProductParam);
    }

    /**
     * 获取商品列表
     */
    @Test
    public void getProductList() {
        if (!BusinessTestUtils.isLogin()) {
            BusinessTestUtils.login();
        }

        PmsProductQueryParam pmsProductQueryParam = new PmsProductQueryParam();
        pmsProductQueryParam.setKeyword("小米");
        pmsProductQueryParam.setPageNum(1);
        pmsProductQueryParam.setPageSize(10);

        BusinessTestUtils.postAuth(BusinessTestUtils.HTTP_DOMAIN + "/api/product/v1/getProductList", pmsProductQueryParam);
    }
}
