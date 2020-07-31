package com.m4coding.mallforeground.service;

import com.m4coding.mallforeground.dto.OmsOrderConfirmParam;
import com.m4coding.mallforeground.dto.OmsOrderConfirmResult;

/**
 * 订单服务接口
 */
public interface OmsOrderService {

    /**
     * 生成确认订单信息
     * @param omsOrderConfirmParam
     * @return
     */
    OmsOrderConfirmResult orderConfirmInfo(OmsOrderConfirmParam omsOrderConfirmParam);
}
