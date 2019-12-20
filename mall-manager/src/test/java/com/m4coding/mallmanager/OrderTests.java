package com.m4coding.mallmanager;

import com.m4coding.mallmanager.dto.OmsOrderQueryParam;
import com.m4coding.mallmanager.dto.PmsProductParam;
import com.m4coding.mallmanager.utils.BusinessTestUtils;
import org.junit.Test;

public class OrderTests {
    /**
     * 获取订单列表测试
     */
    @Test
    public void getOrderList() {
        if (!BusinessTestUtils.isLogin()) {
            BusinessTestUtils.login();
        }

        OmsOrderQueryParam omsOrderQueryParam = new OmsOrderQueryParam();

        omsOrderQueryParam.setPageNum(1);
        omsOrderQueryParam.setPageSize(10);

        BusinessTestUtils.postAuth(BusinessTestUtils.HTTP_DOMAIN + "/api/order/v1/getList", omsOrderQueryParam);
    }

}
