package com.m4coding.mallforeground.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * 获取确认订单信息-入参
 */
public class OmsOrderConfirmParam {

    @ApiModelProperty(value = "商品列表")
    @NotEmpty(message = "商品列表入参不能为空")
    private List<OmsOrderConfirmProductParam> productList;

    public List<OmsOrderConfirmProductParam> getProductList() {
        return productList;
    }

    public void setProductList(List<OmsOrderConfirmProductParam> productList) {
        this.productList = productList;
    }

    public static class OmsOrderConfirmProductParam {
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
}
