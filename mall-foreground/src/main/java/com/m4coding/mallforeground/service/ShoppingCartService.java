package com.m4coding.mallforeground.service;

import com.m4coding.mallforeground.dto.OmsCartAddParam;
import com.m4coding.mallforeground.dto.OmsCartDeleteParam;
import com.m4coding.mallforeground.dto.OmsCartInfoResult;
import com.m4coding.mallforeground.dto.OmsCartUpdateParam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 购物车相关服务
 */
public interface ShoppingCartService {

    /**
     * 加入购物车
     * @param omsCartAddParam
     * @return
     */
    @Transactional
    boolean addCart(OmsCartAddParam omsCartAddParam) throws Exception;

    /**
     * 从购物车中删除
     * @param omsCartDeleteParam
     * @return
     */
    @Transactional
    boolean deleteInCart(OmsCartDeleteParam omsCartDeleteParam);

    /**
     * 获取购物车信息
     * @return
     */
    OmsCartInfoResult getCartInfo() throws Exception;

    /**
     * 更新购物车信息
     * @param omsCartUpdateParam
     * @return
     * @throws Exception
     */
    @Transactional
    boolean updateCartInfo(OmsCartUpdateParam omsCartUpdateParam) throws Exception;
}
