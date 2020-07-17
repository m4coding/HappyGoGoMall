package com.m4coding.mallforeground.utils;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import cn.hutool.setting.Setting;
import com.m4coding.mallforeground.bean.LoginBean;

import java.util.HashMap;

/**
 * 通用任务测试需要的工具方法
 */
public class BusinessTestUtils {

    public static final String HTTP_DOMAIN = "http://localhost:8081";

    private static final int TIMEOUT = 20000; //毫秒
    private static final String SETTING_PATH = "resource/test.setting";

    /**
     * 登录
     */
    public static void login() {
        Console.error("###### 登录操作######");
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("userName", "yyy");
        paramMap.put("password", "yyy");

        String result = HttpRequest.post(HTTP_DOMAIN + "/api/user/v1/login")
                .body(JSONUtil.toJsonStr(paramMap))
                .execute().body();

        JSON json = JSONUtil.parse(result);

        if (isSuccess(json)) {
            LoginBean loginBean = json.getByPath("data", LoginBean.class);
            Setting setting = new Setting();
            setting.set("login", "tokenHead", loginBean.getTokenHead());
            setting.set("login", "token", loginBean.getToken());
            setting.store(SETTING_PATH);
        }

        log(result);
    }

    /**
     * 是否已登录
     * @return
     */
    public static boolean isLogin() {
        try {
            Setting setting = new Setting(SETTING_PATH);
            String tokenHead = setting.get("login", "tokenHead");
            String token = setting.get("login", "token");
            if (!StrUtil.isEmpty(tokenHead) && !StrUtil.isEmpty(token)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
    }


    private static String getToken() {
        Setting setting = new Setting(SETTING_PATH);
        String tokenHead = setting.get("login", "tokenHead");
        String token = setting.get("login", "token");
        return tokenHead + " " + token;
    }

    private static HttpRequest wrapAuthHeader(HttpRequest httpRequest) {
        return httpRequest.header("Authorization", getToken());
    }

    /**
     * 打印
     * @param str
     */
    public static void log(String str) {
        if (JSONUtil.isJson(str)) {
            Console.error(JSONUtil.formatJsonStr(str));
        } else {
            Console.error(str);
        }
    }

    private static boolean isSuccess(JSON json) {
        if (json != null) {
            Object object = json.getByPath("code");
            if (object instanceof Integer) {
                Integer code = (Integer) object;
                if (code == 20000) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void postAuth(String url, Object param) {
        Console.error("###### 提交参数 ######");
        String paramBody = JSONUtil.toJsonStr(param);
        log(paramBody);

        String result = wrapAuthHeader(HttpRequest.post(url))
                .body(paramBody)
                .execute().body();

        Console.error("###### 返回结果 ######");

        log(result);
    }
}
