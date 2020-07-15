package com.m4coding.mallforeground.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.m4coding.mallforeground.bo.MyUserDetails;
import com.m4coding.mallforeground.dto.UmsUpdateUserParam;
import com.m4coding.mallforeground.dto.UmsUpdateUserPwParam;
import com.m4coding.mallforeground.dto.UmsUserRegisterParam;
import com.m4coding.mallforeground.service.UmsUserService;
import com.m4coding.mallmbg.mbg.mapper.*;
import com.m4coding.mallmbg.mbg.model.*;
import com.m4coding.mallsecurity.utils.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author m4coding
 *
 * 用户服务实现
 */
@Service
public class UmsUserServiceImpl implements UmsUserService, UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsUserServiceImpl.class);


    @Autowired
    private UmsUserMapper umsUserMapper;
    @Autowired
    private UmsUserAuthMapper umsUserAuthMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public String login(String userName, String password) throws AuthenticationException {
        String token = null;

        UmsUserExample umsUserExample = new UmsUserExample();
        umsUserExample.createCriteria().andUserNameEqualTo(userName);
        List<UmsUser> list = umsUserMapper.selectByExample(umsUserExample);
        if (null == list || list.isEmpty()) {
            LOGGER.error("用户不存在：{}", userName);
            return null;
        }

        UmsUserAuthExample umsUserAuthExample = new UmsUserAuthExample();
        umsUserAuthExample.createCriteria().andUserIdEqualTo(list.get(0).getUserId());
        List<UmsUserAuth> authList = umsUserAuthMapper.selectByExample(umsUserAuthExample);
        if (!CollectionUtil.isEmpty(authList)) {
            UmsUserAuth umsUserAuth = authList.get(0);
            //判断激活状态
            if (!umsUserAuth.getIfVerify()) {
                throw new BadCredentialsException("账号未被激活");
            }

            //此步类似实现AuthenticationManager鉴权
            MyUserDetails myUserDetails = new MyUserDetails(list.get(0), umsUserAuth);;
            if(!passwordEncoder.matches(password, myUserDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            //生成authentication，并保存在SecurityContext中
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(myUserDetails, null, myUserDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //生成JWT token
            token = jwtTokenUtil.generateToken(myUserDetails);
        }

        return token;
    }

    @Override
    public UmsUser register(UmsUserRegisterParam umsUserRegisterParam) {

        UmsUser umsUser = new UmsUser();
        umsUser.setUserName(umsUserRegisterParam.getIdentity());
        umsUser.setStatus(1);

        //查询是否有相同用户名的用户
        UmsUserExample umsUserExample = new UmsUserExample();
        umsUserExample.createCriteria().andUserNameEqualTo(umsUser.getUserName());
        List<UmsUser> umsUserList = umsUserMapper.selectByExample(umsUserExample);
        if (umsUserList.size() > 0) {
            return null;
        }

        //获取插入后返回的userId
        int userId = umsUserMapper.insert(umsUser);

        if (userId > 0) {
            String encodedCertificate = passwordEncoder.encode(umsUserRegisterParam.getCertificate());

            //关联用户认证表
            UmsUserAuth umsUserAuth = new UmsUserAuth();
            umsUserAuth.setUserId(umsUser.getUserId());
            umsUserAuth.setIdentityType(umsUserRegisterParam.getIdentityType());
            umsUserAuth.setIdentity(umsUserRegisterParam.getIdentity());
            umsUserAuth.setCertificate(encodedCertificate);
            umsUserAuth.setIfVerify(true); //默认为认证激活状态

            umsUserAuthMapper.insert(umsUserAuth);

            return umsUser;
        } else {
            LOGGER.error("注册异常：umsUserMapper.insert {}", umsUser.getUserName());
            return null;
        }

    }

    @Override
    public UmsUser getUserInfo(String userName) {
        UmsUserExample umsUserExample = new UmsUserExample();
        umsUserExample.createCriteria().andUserNameEqualTo(userName);
        List<UmsUser> list = umsUserMapper.selectByExample(umsUserExample);
        if (!CollUtil.isEmpty(list)) {
            return list.get(0);
        }

        return null;
    }

    @Override
    public int updateInfo(UmsUpdateUserParam updateUserManagerParam) {
        UmsUser umsUser = new UmsUser();
        umsUser.setUserId(updateUserManagerParam.getUserId().intValue());

        return umsUserMapper.updateByPrimaryKeySelective(umsUser);
    }

    @Override
    public int updatePassword(UmsUpdateUserPwParam updatePasswordParam) {
        if(StrUtil.isEmpty(updatePasswordParam.getUserName())
                ||StrUtil.isEmpty(updatePasswordParam.getOldPassword())
                ||StrUtil.isEmpty(updatePasswordParam.getNewPassword())){
            return -1;
        }

        UmsUserExample example = new UmsUserExample();
        example.createCriteria().andUserNameEqualTo(updatePasswordParam.getUserName());
        List<UmsUser> userList = umsUserMapper.selectByExample(example);
        if(CollUtil.isEmpty(userList)){
            return -2;
        }

        UmsUser umsUser = userList.get(0);
        UmsUserAuthExample umsUserAuthExample = new UmsUserAuthExample();
        umsUserAuthExample.createCriteria().andUserIdEqualTo(umsUser.getUserId());
        List<UmsUserAuth> authList = umsUserAuthMapper.selectByExample(umsUserAuthExample);
        if (CollUtil.isEmpty(authList)) {
            return -3;
        }

        UmsUserAuth umsUserAuth = authList.get(0);
        if(!passwordEncoder.matches(updatePasswordParam.getOldPassword(), umsUserAuth.getCertificate())){
            return -4;
        }

        //更新
        umsUserAuth.setCertificate(passwordEncoder.encode(updatePasswordParam.getNewPassword()));
        umsUserAuthMapper.updateByPrimaryKey(umsUserAuth);
        return 1;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UmsUserExample umsUserExample = new UmsUserExample();
        umsUserExample.createCriteria().andUserNameEqualTo(username);
        List<UmsUser> list = umsUserMapper.selectByExample(umsUserExample);

        if (list != null && !list.isEmpty()) {
            UmsUser umsUser = list.get(0);
            UmsUserAuthExample umsUserAuthExample = new UmsUserAuthExample();
            umsUserAuthExample.createCriteria().andUserIdEqualTo(umsUser.getUserId());
            List<UmsUserAuth> authList = umsUserAuthMapper.selectByExample(umsUserAuthExample);
            if (!CollectionUtil.isEmpty(authList)) {
                UmsUserAuth umsUserAuth = authList.get(0);
                return new MyUserDetails(umsUser, umsUserAuth);
            }
        }

        throw new UsernameNotFoundException(String.format("没有该用户 '%s'.", username));
    }
}
