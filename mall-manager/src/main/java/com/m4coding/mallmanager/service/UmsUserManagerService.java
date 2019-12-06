package com.m4coding.mallmanager.service;

import com.m4coding.mallmanager.dto.UmsUpdateUserManagerParam;
import com.m4coding.mallmanager.dto.UmsUpdateUserManagerPwParam;
import com.m4coding.mallmanager.dto.UmsUserManagerRegisterParam;
import com.m4coding.mallmbg.mbg.model.UmsAdmin;

/**
 * 后台管理员服务接口
 */
public interface UmsUserManagerService {

    /**
     * 登录
     * @param adminName  管理员名称
     * @param password   密码
     * @return 返回token
     */
    String login(String adminName, String password);


    /**
     * 注册功能
     */
    UmsAdmin register(UmsUserManagerRegisterParam umsUserManagerRegisterParam);

    /**
     * 获取管理员信息
     * @param adminName
     * @return
     */
    UmsAdmin getAdminInfo(String adminName);

    /**
     * 更新管理员信息
     * @param updateUserManagerParam
     * @return
     */
    int updateInfo(UmsUpdateUserManagerParam updateUserManagerParam);

    /**
     * 修改密码
     */
    int updatePassword(UmsUpdateUserManagerPwParam updatePasswordParam);
}
