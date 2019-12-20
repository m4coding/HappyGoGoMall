package com.m4coding.mallmanager.service;

import com.m4coding.mallbase.api.CommonPage;
import com.m4coding.mallmanager.dto.OmsOrderReturnApplyDetailResult;
import com.m4coding.mallmanager.dto.OmsReturnApplyQueryParam;
import com.m4coding.mallmanager.dto.OmsReturnApplyUpdateParam;
import com.m4coding.mallmbg.mbg.model.OmsOrderReturnApply;

import java.util.List;

/**
 * 订单退货申请管理服务
 */
public interface OmsOrderReturnApplyService {
    /**
     * 分页查询申请
     */
    CommonPage<OmsOrderReturnApply> getList(OmsReturnApplyQueryParam queryParam, Integer pageSize, Integer pageNum);

    /**
     * 批量删除申请
     */
    int delete(List<Long> ids);

    /**
     * 修改申请状态
     */
    int updateStatus(Long id, OmsReturnApplyUpdateParam updateParam);

    /**
     * 获取指定售后申请的详情
     */
    OmsOrderReturnApplyDetailResult getDetailItem(Long id);
}
