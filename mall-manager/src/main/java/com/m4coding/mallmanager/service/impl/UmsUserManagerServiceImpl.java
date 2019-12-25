package com.m4coding.mallmanager.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.m4coding.mallmanager.bo.AdminUserDetails;
import com.m4coding.mallmanager.dto.UmsUpdateUserManagerParam;
import com.m4coding.mallmanager.dto.UmsUpdateUserManagerPwParam;
import com.m4coding.mallmanager.dto.UmsUserManagerRegisterParam;
import com.m4coding.mallmanager.service.UmsUserManagerService;
import com.m4coding.mallmbg.mbg.mapper.UmsAdminAuthMapper;
import com.m4coding.mallmbg.mbg.mapper.UmsAdminLoginLogMapper;
import com.m4coding.mallmbg.mbg.mapper.UmsAdminMapper;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author m4coding
 *
 * 后台管理员服务实现
 */
@Service
public class UmsUserManagerServiceImpl implements UmsUserManagerService, UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsUserManagerServiceImpl.class);


    @Autowired
    private UmsAdminMapper umsAdminMapper;
    @Autowired
    private UmsAdminAuthMapper umsAdminAuthMapper;
    @Autowired
    private UmsAdminLoginLogMapper umsAdminLoginLogMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public String login(String adminName, String password) throws AuthenticationException {
        String token = null;

        UmsAdminExample umsAdminExample = new UmsAdminExample();
        umsAdminExample.createCriteria().andAdminNameEqualTo(adminName);
        List<UmsAdmin> list = umsAdminMapper.selectByExample(umsAdminExample);
        if (null == list || list.isEmpty()) {
            return null;
        }

        UmsAdminAuthExample umsAdminAuthExample = new UmsAdminAuthExample();
        umsAdminAuthExample.createCriteria().andAdminIdEqualTo(list.get(0).getAdminId());
        List<UmsAdminAuth> authList = umsAdminAuthMapper.selectByExample(umsAdminAuthExample);
        if (!CollectionUtil.isEmpty(authList)) {
            UmsAdminAuth umsAdminAuth = authList.get(0);
            //判断激活状态
            if (!umsAdminAuth.getIfVerify()) {
                throw new BadCredentialsException("账号未被激活");
            }

            //此步类似实现AuthenticationManager鉴权
            AdminUserDetails userDetails = new AdminUserDetails(list.get(0), umsAdminAuth);;
            if(!passwordEncoder.matches(password, userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            //生成authentication，并保存在SecurityContext中
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //生成JWT token
            token = jwtTokenUtil.generateToken(userDetails);
            insertLoginLog(list.get(0));
        }

        return token;
    }

    //登录记录
    private void insertLoginLog(UmsAdmin umsAdmin) {
        UmsAdminLoginLog umsAdminLoginLog = new UmsAdminLoginLog();
        umsAdminLoginLog.setAdminId(umsAdmin.getAdminId().longValue());
        umsAdminLoginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            umsAdminLoginLog.setIp(request.getRemoteAddr());
        }
        //插入数据库
        umsAdminLoginLogMapper.insert(umsAdminLoginLog);
    }

    @Override
    public UmsAdmin register(UmsUserManagerRegisterParam umsUserManagerRegisterParam) {

        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setAdminName(umsUserManagerRegisterParam.getIdentity());
        umsAdmin.setAdminStatus(1);

        //查询是否有相同用户名的用户
        UmsAdminExample umsAdminExample = new UmsAdminExample();
        umsAdminExample.createCriteria().andAdminNameEqualTo(umsAdmin.getAdminName());
        List<UmsAdmin> umsAdminList = umsAdminMapper.selectByExample(umsAdminExample);
        if (umsAdminList.size() > 0) {
            return null;
        }

        //获取插入后返回的adminId
        int adminId = umsAdminMapper.insert(umsAdmin);

        if (adminId > 0) {
            String encodedCertificate = passwordEncoder.encode(umsUserManagerRegisterParam.getCertificate());

            //关联管理员认证表
            UmsAdminAuth umsAdminAuth = new UmsAdminAuth();
            umsAdminAuth.setAdminId(umsAdmin.getAdminId());
            umsAdminAuth.setIdentityType(umsUserManagerRegisterParam.getIdentityType());
            umsAdminAuth.setIdentity(umsUserManagerRegisterParam.getIdentity());
            umsAdminAuth.setCertificate(encodedCertificate);
            umsAdminAuth.setIfVerify(false); //未认证状态

            umsAdminAuthMapper.insert(umsAdminAuth);

            return umsAdmin;
        } else {
            LOGGER.error("注册异常：umsAdminMapper.insert {}", umsAdmin.getAdminName());
            return null;
        }
    }

    @Override
    public UmsAdmin getAdminInfo(String adminName) {
        UmsAdminExample umsAdminExample = new UmsAdminExample();
        umsAdminExample.createCriteria().andAdminNameEqualTo(adminName);
        List<UmsAdmin> list = umsAdminMapper.selectByExample(umsAdminExample);
        if (!CollUtil.isEmpty(list)) {
            return list.get(0);
        }

        return null;
    }

    @Override
    public int updateInfo(UmsUpdateUserManagerParam updateUserManagerParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setAdminId(updateUserManagerParam.getAdminId().intValue());

        return umsAdminMapper.updateByPrimaryKeySelective(umsAdmin);
    }

    @Override
    public int updatePassword(UmsUpdateUserManagerPwParam updatePasswordParam) {
        if(StrUtil.isEmpty(updatePasswordParam.getAdminName())
                ||StrUtil.isEmpty(updatePasswordParam.getOldPassword())
                ||StrUtil.isEmpty(updatePasswordParam.getNewPassword())){
            return -1;
        }

        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andAdminNameEqualTo(updatePasswordParam.getAdminName());
        List<UmsAdmin> adminList = umsAdminMapper.selectByExample(example);
        if(CollUtil.isEmpty(adminList)){
            return -2;
        }

        UmsAdmin umsAdmin = adminList.get(0);
        UmsAdminAuthExample umsAdminAuthExample = new UmsAdminAuthExample();
        umsAdminAuthExample.createCriteria().andAdminIdEqualTo(umsAdmin.getAdminId());
        List<UmsAdminAuth> authList = umsAdminAuthMapper.selectByExample(umsAdminAuthExample);
        if (CollUtil.isEmpty(authList)) {
            return -3;
        }

        UmsAdminAuth umsAdminAuth = authList.get(0);
        if(!passwordEncoder.matches(updatePasswordParam.getOldPassword(), umsAdminAuth.getCertificate())){
            return -4;
        }

        //更新
        umsAdminAuth.setCertificate(passwordEncoder.encode(updatePasswordParam.getNewPassword()));
        umsAdminAuthMapper.updateByPrimaryKey(umsAdminAuth);
        return 1;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UmsAdminExample umsAdminExample = new UmsAdminExample();
        umsAdminExample.createCriteria().andAdminNameEqualTo(username);
        List<UmsAdmin> list = umsAdminMapper.selectByExample(umsAdminExample);

        if (list != null && !list.isEmpty()) {
            UmsAdmin umsAdmin = list.get(0);
            UmsAdminAuthExample umsAdminAuthExample = new UmsAdminAuthExample();
            umsAdminAuthExample.createCriteria().andAdminIdEqualTo(umsAdmin.getAdminId());
            List<UmsAdminAuth> authList = umsAdminAuthMapper.selectByExample(umsAdminAuthExample);
            if (!CollectionUtil.isEmpty(authList)) {
                UmsAdminAuth umsAdminAuth = authList.get(0);
                return new AdminUserDetails(umsAdmin, umsAdminAuth);
            }
        }

        throw new UsernameNotFoundException(String.format("没有该用户 '%s'.", username));
    }
}
