package com.m4coding.mallforeground.service;

import com.m4coding.mallforeground.dto.AreaParams;
import com.m4coding.mallforeground.dto.CommonAddressResult;
import com.m4coding.mallmbg.mbg.model.UmsUserReceiverAddress;

import java.util.List;

/**
 * 用户收货地址服务
 */
public interface UmsUserReceiverAddressService {
    /**
     * 添加收货地址
     */
    int add(UmsUserReceiverAddress address);

    /**
     * 删除收货地址
     * @param id 地址表的id
     */
    int delete(Long id);

    /**
     * 修改收货地址
     * @param id 地址表的id
     * @param address 修改的收货地址信息
     */
    int update(Long id, UmsUserReceiverAddress address);

    /**
     * 返回当前用户的收货地址
     */
    List<UmsUserReceiverAddress> list();

    /**
     * 获取地址详情
     * @param id 地址id
     */
    UmsUserReceiverAddress getItem(Long id);

    /**
     * 获取区域地址
     * @param areaParams
     * @return
     */
    List<CommonAddressResult> getAreaAddress(AreaParams areaParams) throws Exception;
}
