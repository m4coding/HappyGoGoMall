package com.m4coding.mallforeground.dto;

import com.m4coding.mallmbg.mbg.model.UmsUserReceiverAddress;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 确认订单结果信息
 */
public class OmsOrderConfirmResult {

    @ApiModelProperty(value = "商品列表")
    private List<ConfirmOrderProductInfoBean> productList;
    @ApiModelProperty(value = "用户的默认收货地址")
    private UmsUserReceiverAddress userReceiverAddress;

    public List<ConfirmOrderProductInfoBean> getProductList() {
        return productList;
    }

    public void setProductList(List<ConfirmOrderProductInfoBean> productList) {
        this.productList = productList;
    }

    public static class ConfirmOrderProductInfoBean {
        @ApiModelProperty(value = "商品图片")
        private String imageUrl;

        @ApiModelProperty(value = "商品名称")
        private String productName;

        @ApiModelProperty(value = "商品原始价格")
        private String productOrgPrice;

        @ApiModelProperty(value = "商品现在的价格")
        private String productPrice;

        @ApiModelProperty(value = "商品sku id")
        private Long productSkuId;

        @ApiModelProperty(value = "库存")
        private Long stock;

        @ApiModelProperty(value = "数量")
        private Long quantity;

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

        public String getProductOrgPrice() {
            return productOrgPrice;
        }

        public void setProductOrgPrice(String productOrgPrice) {
            this.productOrgPrice = productOrgPrice;
        }

        public String getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(String productPrice) {
            this.productPrice = productPrice;
        }

        public Long getProductSkuId() {
            return productSkuId;
        }

        public void setProductSkuId(Long productSkuId) {
            this.productSkuId = productSkuId;
        }

        public Long getStock() {
            return stock;
        }

        public void setStock(Long stock) {
            this.stock = stock;
        }

        public Long getQuantity() {
            return quantity;
        }

        public void setQuantity(Long quantity) {
            this.quantity = quantity;
        }
    }
}
