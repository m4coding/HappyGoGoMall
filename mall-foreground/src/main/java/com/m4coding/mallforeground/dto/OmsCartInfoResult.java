package com.m4coding.mallforeground.dto;

import com.m4coding.mallmbg.mbg.model.OmsCartItem;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 获取购物车信息结果
 */
public class OmsCartInfoResult {

    @ApiModelProperty(value = "商品列表")
    private List<CartProductInfoBean> productList;

    public List<CartProductInfoBean> getProductList() {
        return productList;
    }

    public void setProductList(List<CartProductInfoBean> productList) {
        this.productList = productList;
    }

    public static class CartProductInfoBean {
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

        @ApiModelProperty(value = "商品的选中状态，0表示未选中，1表示选中")
        private int itemStatus;

        @ApiModelProperty(value = "品牌id")
        private String brandId;

        @ApiModelProperty(value = "分类id")
        private String categoryId;

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

        public int getItemStatus() {
            return itemStatus;
        }

        public void setItemStatus(int itemStatus) {
            this.itemStatus = itemStatus;
        }

        public String getBrandId() {
            return brandId;
        }

        public void setBrandId(String brandId) {
            this.brandId = brandId;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }
    }
}
