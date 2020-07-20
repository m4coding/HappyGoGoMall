package com.m4coding.mallforeground.service;

import com.m4coding.mallforeground.dto.product.ProductDetailsParam;
import com.m4coding.mallforeground.dto.product.ProductDetailsResult;

/**
 * 商品服务接口
 */
public interface ProductService {
    ProductDetailsResult getProductDetails(ProductDetailsParam productDetailsParam) throws Exception;
}
