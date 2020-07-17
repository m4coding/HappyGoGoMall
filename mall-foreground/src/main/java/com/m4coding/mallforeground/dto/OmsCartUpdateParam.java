package com.m4coding.mallforeground.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 购物车更新入参
 */
public class OmsCartUpdateParam {
    @ApiModelProperty(value = "更新的商品参数集合", required = true)
    @NotEmpty(message = "更新的商品参数集合不能为空")
    private List<OmsCartUpdateChildParam> productList;

    public List<OmsCartUpdateChildParam> getProductList() {
        return productList;
    }

    public void setProductList(List<OmsCartUpdateChildParam> productList) {
        this.productList = productList;
    }

    public static class OmsCartUpdateChildParam {
        @ApiModelProperty(value = "商品id", required = true)
        private Long productSkuId;

        @ApiModelProperty(value = "加购的商品数量", required = true)
        private Long quantity;

        @ApiModelProperty(value = "商品在购物车中的状态: 0-新增（未选中）, 1-选中", required = true)
        private Integer itemStatus;

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

        public Integer getItemStatus() {
            return itemStatus;
        }

        public void setItemStatus(Integer itemStatus) {
            this.itemStatus = itemStatus;
        }
    }


}
