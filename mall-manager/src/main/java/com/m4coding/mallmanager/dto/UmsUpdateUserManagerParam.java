package com.m4coding.mallmanager.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 * 更新管理员信息
 */
public class UmsUpdateUserManagerParam {

    @ApiModelProperty(value = "管理员id")
    private Long adminId;

    @ApiModelProperty(value = "管理员名称")
    private String adminName;

    @ApiModelProperty(value = "电子邮箱")
    private String email;

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }
}
