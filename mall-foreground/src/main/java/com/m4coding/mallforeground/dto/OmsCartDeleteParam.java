package com.m4coding.mallforeground.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 购物车商品删除入参
 */
public class OmsCartDeleteParam {
    @ApiModelProperty(value = "商品id", required = true)
    @NotNull(message = "商品id列表不能为空")
    private List<Long> productSkuIds;

    public List<Long> getProductSkuIds() {
        return productSkuIds;
    }

    public void setProductSkuIds(List<Long> productSkuIds) {
        this.productSkuIds = productSkuIds;
    }
}
