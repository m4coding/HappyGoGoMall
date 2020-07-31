package com.m4coding.mallforeground.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

/**
 * 获取确认订单信息-入参
 */
public class OmsOrderConfirmParam {

    @ApiModelProperty(value = "商品列表")
    @NotEmpty
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

    /**
     * 确认订单时计算的金额
     */
    public static class OmsOrderConfirmCalculateSum {
        //订单商品总金额
        private BigDecimal totalProductAmount;
        //运费
        private BigDecimal freightAmount;
        //应付金额
        private BigDecimal payAmount;

        public BigDecimal getTotalProductAmount() {
            return totalProductAmount;
        }

        public void setTotalProductAmount(BigDecimal totalProductAmount) {
            this.totalProductAmount = totalProductAmount;
        }

        public BigDecimal getFreightAmount() {
            return freightAmount;
        }

        public void setFreightAmount(BigDecimal freightAmount) {
            this.freightAmount = freightAmount;
        }

        public BigDecimal getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(BigDecimal payAmount) {
            this.payAmount = payAmount;
        }
    }
}
