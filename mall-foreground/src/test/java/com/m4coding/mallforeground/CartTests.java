package com.m4coding.mallforeground;

import com.m4coding.mallforeground.dto.OmsCartAddParam;
import com.m4coding.mallforeground.dto.OmsCartDeleteParam;
import com.m4coding.mallforeground.dto.OmsCartListQueryParam;
import com.m4coding.mallforeground.dto.OmsCartUpdateParam;
import com.m4coding.mallforeground.utils.BusinessTestUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 购物车测试
 */
public class CartTests {

    /**
     * 加入购物车
     */
    @Test
    public void addCart() {
        if (!BusinessTestUtils.isLogin()) {
            BusinessTestUtils.login();
        }

        OmsCartAddParam omsCartAddParam = new OmsCartAddParam();
        omsCartAddParam.setQuantity(4L);
        omsCartAddParam.setProductSkuId(7L);

        BusinessTestUtils.postAuth(BusinessTestUtils.HTTP_DOMAIN + "/api/cart/v1/add", omsCartAddParam);
    }

    @Test
    public void deleteFromCart() {
        if (!BusinessTestUtils.isLogin()) {
            BusinessTestUtils.login();
        }

        OmsCartDeleteParam omsCartDeleteParam = new OmsCartDeleteParam();
        List<Long> list = new ArrayList<>();
        list.add(7L);
        list.add(6L);
        omsCartDeleteParam.setProductSkuIds(list);

        BusinessTestUtils.postAuth(BusinessTestUtils.HTTP_DOMAIN + "/api/cart/v1/delete", omsCartDeleteParam);
    }


    @Test
    public void getCartInfo() {
        if (!BusinessTestUtils.isLogin()) {
            BusinessTestUtils.login();
        }

        OmsCartListQueryParam omsCartListQueryParam = new OmsCartListQueryParam();

        BusinessTestUtils.postAuth(BusinessTestUtils.HTTP_DOMAIN + "/api/cart/v1/getCartInfo", omsCartListQueryParam);
    }

    @Test
    public void updateCartInfo() {
        if (!BusinessTestUtils.isLogin()) {
            BusinessTestUtils.login();
        }

        OmsCartUpdateParam omsCartUpdateParam = new OmsCartUpdateParam();
        List<OmsCartUpdateParam.OmsCartUpdateChildParam> list = new ArrayList<>();

        OmsCartUpdateParam.OmsCartUpdateChildParam omsCartUpdateChildParam = new OmsCartUpdateParam.OmsCartUpdateChildParam();
        omsCartUpdateChildParam.setQuantity(1L);
        omsCartUpdateChildParam.setProductSkuId(6L);
        omsCartUpdateChildParam.setItemStatus(0);
        list.add(omsCartUpdateChildParam);

        OmsCartUpdateParam.OmsCartUpdateChildParam omsCartUpdateChildParam22 = new OmsCartUpdateParam.OmsCartUpdateChildParam();
        omsCartUpdateChildParam22.setQuantity(1L);
        omsCartUpdateChildParam22.setProductSkuId(7L);
        omsCartUpdateChildParam22.setItemStatus(0);
        list.add(omsCartUpdateChildParam22);

        omsCartUpdateParam.setProductList(list);

        BusinessTestUtils.postAuth(BusinessTestUtils.HTTP_DOMAIN + "/api/cart/v1/updateCartInfo", omsCartUpdateParam);
    }
}
