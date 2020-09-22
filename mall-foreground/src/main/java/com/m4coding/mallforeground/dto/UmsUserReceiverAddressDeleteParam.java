package com.m4coding.mallforeground.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 * 地址删除入参
 */
@ApiModel(description = "地址删除入参")
public class UmsUserReceiverAddressDeleteParam {

    @ApiModelProperty(value = "要删除的地址id")
    private long addressId;

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }
}
