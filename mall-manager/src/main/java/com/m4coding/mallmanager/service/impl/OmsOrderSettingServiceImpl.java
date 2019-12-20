package com.m4coding.mallmanager.service.impl;

import com.m4coding.mallmanager.service.OmsOrderSettingService;
import com.m4coding.mallmbg.mbg.mapper.OmsOrderSettingMapper;
import com.m4coding.mallmbg.mbg.model.OmsOrderSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单统一设置具体实现类
 */
@Service
public class OmsOrderSettingServiceImpl implements OmsOrderSettingService {

    @Autowired
    private OmsOrderSettingMapper omsOrderSettingMapper;

    @Override
    public OmsOrderSetting getItem(Long id) {
        return omsOrderSettingMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Long id, OmsOrderSetting orderSetting) {
        orderSetting.setId(id);
        return omsOrderSettingMapper.updateByPrimaryKey(orderSetting);
    }
}
