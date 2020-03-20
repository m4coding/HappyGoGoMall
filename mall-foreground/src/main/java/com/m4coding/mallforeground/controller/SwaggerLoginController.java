package com.m4coding.mallforeground.controller;

import com.google.common.base.Strings;
import com.m4coding.mallbase.api.CommonResult;
import com.m4coding.mallforeground.filter.SwaggerUIFilter;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * swagger-ui登陆页面
 *
 */
@Controller
public class SwaggerLoginController {

    private String username;
    private String password;

    public SwaggerLoginController() {
        this.username = "admin";
        this.password = "admin";
    }

    /**
     * 展示登陆页面
     *
     * @return 页面
     */
    @RequestMapping(value = "swagger_login", method = RequestMethod.GET)
    public ModelAndView showLoginPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("swagger_login");
        byte[] result2 = Base64.decodeBase64(request.getParameter("rurl"));
        String rurl = new String(result2);
        modelAndView.addObject("rurl", rurl);
        if (request.getParameter("px") != null) {
            byte[] pxByte = Base64.decodeBase64(request.getParameter("px"));
            String px = new String(pxByte);
            modelAndView.addObject("px", px);
        }

        return modelAndView;
    }


    /**
     * 登陆验证
     */
    @RequestMapping(value = "swagger/login/check", method = RequestMethod.POST)
    @ApiOperation(value = "登陆验证", notes = "登陆验证", response = CommonResult.class)
    @ResponseBody
    public CommonResult loginCheck(@RequestParam("username") String username,
                                     @RequestParam("password") String password,
                                     HttpSession session) {

        if (this.username.equals(username)
                && this.password.equals(password)) {
            session.setAttribute(SwaggerUIFilter.SWAGGER_LOGIN_FLAG, username);
            return CommonResult.success(null);
        }
        return CommonResult.failed("用户名或密码不正确");
    }

}
