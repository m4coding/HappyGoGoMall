package com.m4coding.mallforeground.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Positive;

/**
 * 加入购物车参数
 */
public class OmsCartAddParam {

    @ApiModelProperty(value = "商品id", required = true)
    private Long productSkuId;

    @ApiModelProperty(value = "加购的商品数量", required = true)
    @Positive(message = "加购的商品数量不能小于0")
    private Long quantity;

    public Long getProductSkuId() {
        return productSkuId;
    }

    public void setProductSkuId(Long productSkuId) {
        this.productSkuId = productSkuId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
