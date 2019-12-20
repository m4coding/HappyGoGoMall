package com.m4coding.mallmanager.service;

import com.m4coding.mallbase.api.CommonPage;
import com.m4coding.mallmanager.dto.OmsOrderDeliveryParam;
import com.m4coding.mallmanager.dto.OmsOrderDetail;
import com.m4coding.mallmanager.dto.OmsOrderQueryParam;
import com.m4coding.mallmanager.dto.OmsReceiverInfoParam;
import com.m4coding.mallmbg.mbg.model.OmsOrder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单管理相关的服务
 */
public interface OmsOrderService {

    CommonPage<OmsOrder> getList(OmsOrderQueryParam omsOrderQueryParam);

    /**
     * 批量发货
     */
    @Transactional
    int delivery(List<OmsOrderDeliveryParam> deliveryParamList);

    /**
     * 批量关闭订单
     */
    @Transactional
    int close(List<Long> ids, String note);

    /**
     * 批量删除订单
     */
    int delete(List<Long> ids);

    /**
     * 获取指定订单详情
     */
    OmsOrderDetail getDetail(Long id);

    /**
     * 修改订单收货人信息
     */
    @Transactional
    int updateReceiverInfo(OmsReceiverInfoParam receiverInfoParam);


    /**
     * 修改订单备注
     */
    @Transactional
    int updateNote(Long id, String note, Integer status);
}
