package com.m4coding.mallforeground;

import com.m4coding.mallforeground.dto.product.ProductDetailsParam;
import com.m4coding.mallforeground.utils.BusinessTestUtils;
import org.junit.Test;

/**
 * 商品测试
 */
public class ProductTests {

    /**
     * 获取商品详情
     */
    @Test
    public void getProductDetails() {
        if (!BusinessTestUtils.isLogin()) {
            BusinessTestUtils.login();
        }

        ProductDetailsParam productDetailsParam = new ProductDetailsParam();
        productDetailsParam.setProductSkuId(100);

        BusinessTestUtils.postAuth(BusinessTestUtils.HTTP_DOMAIN + "/api/product/v1/getProductDetail", productDetailsParam);
    }
}
