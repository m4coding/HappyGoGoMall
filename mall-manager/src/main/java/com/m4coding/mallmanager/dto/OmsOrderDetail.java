package com.m4coding.mallmanager.dto;

import com.m4coding.mallmbg.mbg.model.OmsOrder;
import com.m4coding.mallmbg.mbg.model.OmsOrderItem;
import com.m4coding.mallmbg.mbg.model.OmsOrderOperateHistory;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 订单详情信息
 */
public class OmsOrderDetail extends OmsOrder {
    @ApiModelProperty(value = "订单商品信息")
    private List<OmsOrderItem> orderItemList;
    @ApiModelProperty(value = "订单操作记录信息")
    private List<OmsOrderOperateHistory> historyList;

    public List<OmsOrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OmsOrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public List<OmsOrderOperateHistory> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<OmsOrderOperateHistory> historyList) {
        this.historyList = historyList;
    }
}
