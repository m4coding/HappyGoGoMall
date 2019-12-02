package com.m4coding.mallmbg.mbg.model;

import java.io.Serializable;

public class UmsAdmin implements Serializable {
    /**
     * 管理员ID
     *
     * @mbg.generated
     */
    private Integer adminId;

    /**
     * 管理员名称
     *
     * @mbg.generated
     */
    private String adminName;

    /**
     * 管理员状态
     *
     * @mbg.generated
     */
    private Integer adminStatus;

    private static final long serialVersionUID = 1L;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public Integer getAdminStatus() {
        return adminStatus;
    }

    public void setAdminStatus(Integer adminStatus) {
        this.adminStatus = adminStatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", adminId=").append(adminId);
        sb.append(", adminName=").append(adminName);
        sb.append(", adminStatus=").append(adminStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}