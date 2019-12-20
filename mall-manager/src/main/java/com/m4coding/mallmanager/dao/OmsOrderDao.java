package com.m4coding.mallmanager.dao;

import com.m4coding.mallmanager.dto.OmsOrderDeliveryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单中心dao
 */
public interface OmsOrderDao {
    /**
     * 批量发货
     * @param list
     * @return
     */
    int delivery(@Param("list") List<OmsOrderDeliveryParam> list);
}
