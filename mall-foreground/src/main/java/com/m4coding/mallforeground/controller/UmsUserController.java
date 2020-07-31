package com.m4coding.mallforeground.controller;

import cn.hutool.core.util.StrUtil;
import com.m4coding.mallbase.api.CommonResult;
import com.m4coding.mallbase.version.ApiVersion;
import com.m4coding.mallforeground.dto.UmsUserInfo;
import com.m4coding.mallforeground.dto.UmsUserLoginParam;
import com.m4coding.mallforeground.dto.UmsUserRegisterParam;
import com.m4coding.mallforeground.service.UmsUserService;
import com.m4coding.mallmbg.mbg.model.UmsUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 前台用户管理控制器
 */
@Api(tags = "UmsUserController", description = "用户相关")
@RestController
@RequestMapping("api/user/{version}")
public class UmsUserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsUserController.class);

    @Autowired
    private UmsUserService umsUserService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;


    @ApiOperation(value = "登录，成功后会返回token")
    @ApiVersion(1)
    @PostMapping(value = "/login")
    public CommonResult login(@ApiParam(value = "版本号", allowableValues = "v1", required = true)
                              @PathVariable(value = "version") String version,
                              @Valid @RequestBody UmsUserLoginParam loginParam, BindingResult bindingResult) {
        String token = null;
        String errorMsg = "用户名或密码错误";
        try {
            token = umsUserService.login(loginParam.getUserName(), loginParam.getPassword());
        } catch (AuthenticationException e) {
            errorMsg = e.getMessage();
            LOGGER.error("登录异常:{}", e.getMessage());
        }

        if (StrUtil.isEmpty(token)) {
            return CommonResult.validateFailed(errorMsg);
        }

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }


    @ApiOperation(value = "注册用户")
    @ApiVersion(1)
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult<UmsUser> register(@ApiParam(value = "版本号", allowableValues = "v1", required = true)
                                          @PathVariable(value = "version") String version,
                                          @Valid @RequestBody UmsUserRegisterParam umsUserRegisterParam,
                                          BindingResult bindingResult) {
        UmsUser umsUser = umsUserService.register(umsUserRegisterParam);

        if (null == umsUser) {
            return CommonResult.failed();
        }

        return CommonResult.success(umsUser);
    }

    @ApiOperation(value = "获取用户信息")
    @ApiVersion(1)
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    public CommonResult<UmsUserInfo> register(@ApiParam(value = "版本号", allowableValues = "v1", required = true)
                                          @PathVariable(value = "version") String version) {
        UmsUser umsUser = umsUserService.getCurrentUser();

        if (null == umsUser) {
            return CommonResult.failed();
        }

        UmsUserInfo umsUserInfo = new UmsUserInfo();
        umsUserInfo.setUserId(umsUser.getUserId());
        umsUserInfo.setUserName(umsUser.getUserName());

        return CommonResult.success(umsUserInfo);
    }
}
