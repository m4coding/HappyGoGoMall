package com.m4coding.mallforeground.dto.product;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * 商品详情入参
 */
public class ProductDetailsParam {

    @ApiModelProperty(value = "商品sku id")
    @NotNull(message = "商品sku id不能为空")
    private Integer productSkuId; //商品sku id

    public Integer getProductSkuId() {
        return productSkuId;
    }

    public void setProductSkuId(Integer productSkuId) {
        this.productSkuId = productSkuId;
    }
}
