package com.m4coding.mallmanager.service;

import com.m4coding.mallbase.api.CommonPage;
import com.m4coding.mallmbg.mbg.model.OmsOrderReturnReason;

import java.util.List;

/**
 * 订单退货原因设置服务
 */
public interface OmsOrderReturnReasonSettingService {

    /**
     * 添加订单原因
     */
    int create(OmsOrderReturnReason returnReason);

    /**
     * 修改退货原因
     */
    int update(Long id, OmsOrderReturnReason returnReason);

    /**
     * 批量删除退货原因
     */
    int delete(List<Long> ids);

    /**
     * 分页获取退货原因
     */
    CommonPage<OmsOrderReturnReason> list(Integer pageSize, Integer pageNum);

    /**
     * 批量修改退货原因状态
     */
    int updateStatus(List<Long> ids, Integer status);

    /**
     * 获取单个退货原因详情信息
     */
    OmsOrderReturnReason getItem(Long id);
}
