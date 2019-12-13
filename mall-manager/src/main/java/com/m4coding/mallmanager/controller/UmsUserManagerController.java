package com.m4coding.mallmanager.controller;

import cn.hutool.core.util.StrUtil;
import com.m4coding.mallbase.api.CommonResult;
import com.m4coding.mallmanager.dto.UmsUserManagerLoginParam;
import com.m4coding.mallmanager.dto.UmsUserManagerRegisterParam;
import com.m4coding.mallmanager.service.UmsUserManagerService;
import com.m4coding.mallbase.version.ApiVersion;
import com.m4coding.mallmbg.mbg.model.UmsAdmin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 后台用户管理控制器
 */
@Api(tags = "UmsUserManagerController", description = "管理员相关")
@RestController
@RequestMapping("api/admin")
public class UmsUserManagerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsUserManagerController.class);

    @Autowired
    private UmsUserManagerService umsUserManagerService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;


    @ApiOperation(value = "登录，成功后会返回token")
    @ApiVersion
    @PostMapping(value = "/{version}/login")
    public CommonResult login(@Valid @RequestBody UmsUserManagerLoginParam loginParam, BindingResult bindingResult) {
        //判断各个入参是否符合条件
        if (bindingResult.hasErrors()) {
            FieldError error = (FieldError) bindingResult.getAllErrors().get(0);
            return CommonResult.validateFailed(error.getDefaultMessage());
        }

        String token = null;
        String errorMsg = "用户名或密码错误";
        try {
            token = umsUserManagerService.login(loginParam.getAdminName(), loginParam.getPassword());
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


    @ApiOperation(value = "注册管理员")
    @ApiVersion(1)
    @RequestMapping(value = "{version}/register", method = RequestMethod.POST)
    public CommonResult<UmsAdmin> register(@Valid @RequestBody UmsUserManagerRegisterParam umsUserManagerRegisterParam,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = (FieldError) bindingResult.getAllErrors().get(0);
            return CommonResult.validateFailed(fieldError.getDefaultMessage());
        }

        UmsAdmin umsAdmin = umsUserManagerService.register(umsUserManagerRegisterParam);

        if (null == umsAdmin) {
            return CommonResult.failed();
        }

        return CommonResult.success(umsAdmin);
    }
}
