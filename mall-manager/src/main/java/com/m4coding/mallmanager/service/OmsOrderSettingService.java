package com.m4coding.mallmanager.service;

import com.m4coding.mallmbg.mbg.model.OmsOrderSetting;

/**
 * 订单统一设置服务
 */
public interface OmsOrderSettingService {
    /**
     * 获取指定订单设置
     */
    OmsOrderSetting getItem(Long id);

    /**
     * 修改指定订单设置
     */
    int update(Long id, OmsOrderSetting orderSetting);
}
