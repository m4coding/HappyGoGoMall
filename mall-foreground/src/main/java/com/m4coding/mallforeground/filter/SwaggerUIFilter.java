package com.m4coding.mallforeground.filter;

import cn.hutool.core.util.StrUtil;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 拦截swagger-ui页面，需要登录
 */
public class SwaggerUIFilter implements Filter {

    public static final String SWAGGER_LOGIN_FLAG = "SWAGGER_LOGIN_OK";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        Object object = session.getAttribute(SWAGGER_LOGIN_FLAG);
        if (object == null) {
            String location = httpServletRequest.getHeader("location"); //获取nginx反向代理的location header
            if (!StrUtil.isEmpty(location)) {
                String requestUrl = new String(Base64.encodeBase64((httpServletRequest.getScheme()
                        + "://" + httpServletRequest.getServerName()
                        + location + httpServletRequest.getRequestURI()).getBytes()));
                // 没有登陆
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + location
                        + "/swagger_login" + "?rurl=" + requestUrl + "&px=" + new String(Base64.
                        encodeBase64(location.getBytes())));
            } else{
                String requestUrl = new String(Base64.
                        encodeBase64((httpServletRequest.getRequestURL().toString()).getBytes()));
                // 没有登陆
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath()
                        + "/swagger_login" + "?rurl=" + requestUrl);
            }
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
