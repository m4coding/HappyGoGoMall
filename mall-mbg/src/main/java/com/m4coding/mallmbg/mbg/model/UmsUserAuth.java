package com.m4coding.mallmbg.mbg.model;

import java.io.Serializable;

public class UmsUserAuth implements Serializable {
    /**
     * 认证ID
     *
     * @mbg.generated
     */
    private Integer authId;

    /**
     * 用户ID
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     * 登录类型
     *
     * @mbg.generated
     */
    private String indentityType;

    /**
     * 识别标识
     *
     * @mbg.generated
     */
    private String indentity;

    /**
     * 授权凭证
     *
     * @mbg.generated
     */
    private String certificate;

    /**
     * 是否验证
     *
     * @mbg.generated
     */
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

    public String getIndentityType() {
        return indentityType;
    }

    public void setIndentityType(String indentityType) {
        this.indentityType = indentityType;
    }

    public String getIndentity() {
        return indentity;
    }

    public void setIndentity(String indentity) {
        this.indentity = indentity;
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
        sb.append(", indentityType=").append(indentityType);
        sb.append(", indentity=").append(indentity);
        sb.append(", certificate=").append(certificate);
        sb.append(", ifVerify=").append(ifVerify);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}