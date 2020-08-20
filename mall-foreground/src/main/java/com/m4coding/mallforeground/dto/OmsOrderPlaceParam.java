package com.m4coding.mallforeground.dto;

import com.m4coding.mallmbg.mbg.model.OmsOrderItem;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

/**
 * 下单入参
 */
public class OmsOrderPlaceParam {

    @ApiModelProperty(value = "商品list入参")
    @NotEmpty(message = "商品列表不能为空")
    private List<ProductParam> productList;

    @ApiModelProperty(value = "收货地址id")
    private Long receiveAddressId;

    public List<ProductParam> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductParam> productList) {
        this.productList = productList;
    }

    public Long getReceiveAddressId() {
        return receiveAddressId;
    }

    public void setReceiveAddressId(Long receiveAddressId) {
        this.receiveAddressId = receiveAddressId;
    }

    public static class ProductParam {
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

    public static class ProductInfoBean extends OmsOrderItem {
        @ApiModelProperty(value = "商品图片")
        private String imageUrl;

        @ApiModelProperty(value = "商品名称")
        private String productName;

        @ApiModelProperty(value = "商品原始价格")
        private BigDecimal productOrgPrice;

        @ApiModelProperty(value = "商品sku id")
        private Long productSkuId;

        @ApiModelProperty(value = "数量")
        private Long quantity;

        @ApiModelProperty(value = "商品真实库存--减掉了锁定库存")
        private Long realStock;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public BigDecimal getProductOrgPrice() {
            return productOrgPrice;
        }

        public void setProductOrgPrice(BigDecimal productOrgPrice) {
            this.productOrgPrice = productOrgPrice;
        }

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

        public Long getRealStock() {
            return realStock;
        }

        public void setRealStock(Long realStock) {
            this.realStock = realStock;
        }
    }
}
