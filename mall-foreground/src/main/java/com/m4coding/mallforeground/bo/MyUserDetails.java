package com.m4coding.mallforeground.bo;

import com.m4coding.mallmbg.mbg.model.UmsUser;
import com.m4coding.mallmbg.mbg.model.UmsUserAuth;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 实现UserDetails接口的AdminUserDetails  （spring security需要）
 */
public class MyUserDetails implements UserDetails {

    private UmsUser umsUser;
    private UmsUserAuth umsUserAuth;


    public MyUserDetails(UmsUser umsUser, UmsUserAuth umsUserAuth) {
        this.umsUser = umsUser;
        this.umsUserAuth = umsUserAuth;
    }

    public UmsUser getUmsUser() {
        return umsUser;
    }

    public UmsUserAuth getUmsUserAuth() {
        return umsUserAuth;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return umsUserAuth.getCertificate();
    }

    @Override
    public String getUsername() {
        return umsUser.getUserName();
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
        return umsUser.getStatus().equals(1);
    }
}
