package com.m4coding.mallforeground;

import com.m4coding.mallforeground.dto.OmsOrderConfirmParam;
import com.m4coding.mallforeground.utils.BusinessTestUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单测试
 */
public class OrderTests {

    @Test
    public void testConfirmOrderInfo() {
        if (!BusinessTestUtils.isLogin()) {
            BusinessTestUtils.login();
        }

        OmsOrderConfirmParam omsOrderConfirmParam = new OmsOrderConfirmParam();

        OmsOrderConfirmParam.OmsOrderConfirmProductParam productParam = new OmsOrderConfirmParam.OmsOrderConfirmProductParam();
        productParam.setQuantity(2L);
        productParam.setProductSkuId(361L);
        List<OmsOrderConfirmParam.OmsOrderConfirmProductParam> list = new ArrayList<>();
        list.add(productParam);
        omsOrderConfirmParam.setProductList(list);

        BusinessTestUtils.postAuth(BusinessTestUtils.HTTP_DOMAIN + "/api/order/v1/getConfirmOrderInfo", omsOrderConfirmParam);
    }
}
