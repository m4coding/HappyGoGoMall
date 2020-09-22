package com.m4coding.mallforeground;

import com.m4coding.mallforeground.dto.AreaParams;
import com.m4coding.mallforeground.dto.product.ProductDetailsParam;
import com.m4coding.mallforeground.utils.BusinessTestUtils;
import org.junit.Test;

/**
 * 地址相关测试
 */
public class AddressTests {

    @Test
    public void testAreaCode() {
        if (!BusinessTestUtils.isLogin()) {
            BusinessTestUtils.login();
        }

        AreaParams areaParams = new AreaParams();
        areaParams.setAreaCode("440000000000");

        BusinessTestUtils.postAuth(BusinessTestUtils.HTTP_DOMAIN + "/api/address/v1/getAreaList", areaParams);
    }
}
