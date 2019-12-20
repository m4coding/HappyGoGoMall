package com.m4coding.mallmanager.dto;


import com.m4coding.mallmbg.mbg.model.OmsCompanyAddress;
import com.m4coding.mallmbg.mbg.model.OmsOrderReturnApply;
import io.swagger.annotations.ApiModelProperty;

/**
 * 售后申请详情信息封装
 */
public class OmsOrderReturnApplyDetailResult extends OmsOrderReturnApply {
    @ApiModelProperty(value = "公司收货地址")
    private OmsCompanyAddress companyAddress;

    public OmsCompanyAddress getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(OmsCompanyAddress companyAddress) {
        this.companyAddress = companyAddress;
    }
}
