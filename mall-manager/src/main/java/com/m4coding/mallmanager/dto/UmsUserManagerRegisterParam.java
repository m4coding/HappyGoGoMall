package com.m4coding.mallmanager.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * 后台管理员入参
 */
@ApiModel(description = "注册管理员入参信息")
public class UmsUserManagerRegisterParam {

    @ApiModelProperty(value = "登录类型  如：admin表示系统用户，phone表示手机登录，weixin表示微信登录，qq表示qq登录", required = true)
    @NotEmpty(message = "登录类型不能为空")
    private String identityType;

    @ApiModelProperty(value = "识别标识  如：登录账号、邮箱地址、手机号、微信号、qq号等", required = true)
    @NotEmpty(message = "识别标识不能为空")
    private String identity;

    @ApiModelProperty(value = "授权凭证  如：登录账号对应的是密码、第三方登录对应的是token", required = true)
    @NotEmpty(message = "授权凭证不能为空")
    private String certificate;

    @ApiModelProperty(value = "是否验证 true表示已被验证，false表示未验证", required = true)
    private Boolean ifVerify;

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public Boolean getIfVerify() {
        return ifVerify;
    }

    public void setIfVerify(Boolean ifVerify) {
        this.ifVerify = ifVerify;
    }
}
