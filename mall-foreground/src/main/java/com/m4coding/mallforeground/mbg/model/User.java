package com.m4coding.mallforeground.mbg.model;

import java.io.Serializable;

public class User implements Serializable {
    /**
     * 用户ID
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     * 用户名
     *
     * @mbg.generated
     */
    private String userName;

    /**
     * 用户状态
     *
     * @mbg.generated
     */
    private Integer status;

    private static final long serialVersionUID = 1L;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}