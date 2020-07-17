package com.m4coding.mallforeground.service;


import com.m4coding.mallforeground.dto.UmsUpdateUserParam;
import com.m4coding.mallforeground.dto.UmsUpdateUserPwParam;
import com.m4coding.mallforeground.dto.UmsUserRegisterParam;
import com.m4coding.mallmbg.mbg.model.UmsAdmin;
import com.m4coding.mallmbg.mbg.model.UmsUser;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户服务接口
 */
public interface UmsUserService {

    /**
     * 登录
     * @param userName  用户名称
     * @param password   密码
     * @return 返回token
     */
    String login(String userName, String password);


    /**
     * 注册功能
     */
    @Transactional
    UmsUser register(UmsUserRegisterParam umsUserManagerRegisterParam);

    /**
     * 获取管理员信息
     * @return
     */
    UmsUser getUserInfo(String adminName);

    /**
     * 更新管理员信息
     * @return
     */
    @Transactional
    int updateInfo(UmsUpdateUserParam updateUserManagerParam);

    /**
     * 修改密码
     */
    @Transactional
    int updatePassword(UmsUpdateUserPwParam updatePasswordParam);

    /**
     * 获取当前登录的用户
     */
    UmsUser getCurrentUser();
}
