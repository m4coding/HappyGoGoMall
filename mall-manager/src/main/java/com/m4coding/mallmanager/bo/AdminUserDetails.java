package com.m4coding.mallmanager.bo;

import com.m4coding.mallmbg.mbg.model.UmsAdmin;
import com.m4coding.mallmbg.mbg.model.UmsAdminAuth;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 实现UserDetails接口的AdminUserDetails  （spring security需要）
 */
public class AdminUserDetails implements UserDetails {

    private UmsAdmin umsAdmin;
    private UmsAdminAuth umsAdminAuth;


    public AdminUserDetails(UmsAdmin umsAdmin, UmsAdminAuth umsAdminAuth) {
        this.umsAdmin = umsAdmin;
        this.umsAdminAuth = umsAdminAuth;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return umsAdminAuth.getCertificate();
    }

    @Override
    public String getUsername() {
        return umsAdmin.getAdminName();
    }

    // 账户是否未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账户是否未锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 密码是否未过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 账户是否激活
    @Override
    public boolean isEnabled() {
        return umsAdmin.getAdminStatus().equals(1);
    }
}
