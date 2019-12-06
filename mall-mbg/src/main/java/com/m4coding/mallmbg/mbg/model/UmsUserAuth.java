package com.m4coding.mallmbg.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class UmsUserAuth implements Serializable {
    @ApiModelProperty(value = "认证ID")
    private Integer authId;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "登录类型  如：admin表示系统用户，phone表示手机登录，weixin表示微信登录，qq表示qq登录")
    private String identityType;

    @ApiModelProperty(value = "识别标识  如：登录账号、邮箱地址、手机号、微信号、qq号等")
    private String identity;

    @ApiModelProperty(value = "授权凭证  如：登录账号对应的是密码、第三方登录对应的是token")
    private String certificate;

    @ApiModelProperty(value = "是否验证   true表示已被验证，false表示未验证")
    private Boolean ifVerify;

    private static final long serialVersionUID = 1L;

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", authId=").append(authId);
        sb.append(", userId=").append(userId);
        sb.append(", identityType=").append(identityType);
        sb.append(", identity=").append(identity);
        sb.append(", certificate=").append(certificate);
        sb.append(", ifVerify=").append(ifVerify);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}