package com.m4coding.mallforeground.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 * 地址入参
 */
@ApiModel(description = "地址入参信息")
public class UmsUserReceiverAddressParam {

    @ApiModelProperty(value = "地址id, 编辑地址时需要用到，添加地址不需要")
    private long addressId;

    @ApiModelProperty(value = "是否默认地址，0-不是 1-是")
    private int isDefault;

    @ApiModelProperty(value = "收货详细地址")
    @NotEmpty(message = "收货详细地址不能为空")
    private String receiverAddr;

    @ApiModelProperty(value = "收货人名字")
    @NotEmpty(message = "收货人名字不能为空")
    private String receiverName;

    @ApiModelProperty(value = "收货人手机号")
    @NotEmpty(message = "收货人手机号不能为空")
    private String receiverPhone;

    @ApiModelProperty(value = "收货人地区")
    @NotEmpty(message = "收货人地区不能为空")
    private String receiverRegion;

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public String getReceiverAddr() {
        return receiverAddr;
    }

    public void setReceiverAddr(String receiverAddr) {
        this.receiverAddr = receiverAddr;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverRegion() {
        return receiverRegion;
    }

    public void setReceiverRegion(String receiverRegion) {
        this.receiverRegion = receiverRegion;
    }
}
