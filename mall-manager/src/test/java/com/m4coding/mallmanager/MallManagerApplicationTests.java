package com.m4coding.mallmanager;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import cn.hutool.setting.Setting;
import com.m4coding.mallmanager.bean.LoginBean;
import com.m4coding.mallmanager.dto.PmsProductParam;
import org.junit.Test;

import java.util.HashMap;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = MallManagerApplication.class,
//        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MallManagerApplicationTests {

    private static final String HTTP_DOMAIN = "http://localhost:8080";
    private static final int TIMEOUT = 20000; //毫秒
    private static final String SETTING_PATH = "resource/test.setting";

    @Test
    public void productCreate() {
        if (!isLogin()) {
            login();
        }

        PmsProductParam pmsProductParam = new PmsProductParam();
        pmsProductParam.setBrandId((long) 2);
        pmsProductParam.setProductName("测试商品");

        postAuth(HTTP_DOMAIN + "/api/product/v1/create", pmsProductParam);
    }

    private static void login() {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("adminName", "m4coding123");
        paramMap.put("password", "m4coding123");

        String result = HttpRequest.post(HTTP_DOMAIN + "/api/admin/v1/login")
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

    private static boolean isLogin() {
        Setting setting = new Setting(SETTING_PATH);
        String tokenHead = setting.get("login", "tokenHead");
        String token = setting.get("login", "token");
        if (!StrUtil.isEmpty(tokenHead) && !StrUtil.isEmpty(token)) {
            return true;
        } else {
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

    //打印
    private static void log(String str) {
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

    private static void postAuth(String url, Object param) {
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
