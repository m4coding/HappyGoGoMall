package com.m4coding.mallforeground.dto.childitem;

import com.m4coding.mallforeground.dto.HomeCommonItemResult;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * viewType: product_card
 */
public class HomeProductCardChildItem {
    private List<Child> items;

    public static class Child {
        private String imageUrl;
        private String productName;
        private String productOrgPrice;
        private String productPrice;
        @ApiModelProperty(value = "商品Id")
        private Long productSkuId;
        @ApiModelProperty(value = "spu Id")
        private Long productSpuId;

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

        public Long getProductSpuId() {
            return productSpuId;
        }

        public void setProductSpuId(Long productSpuId) {
            this.productSpuId = productSpuId;
        }
    }

    public List<Child> getItems() {
        return items;
    }

    public void setItems(List<Child> items) {
        this.items = items;
    }

    public static HomeCommonItemResult<HomeProductCardChildItem> createItem() {
        HomeCommonItemResult result = new HomeCommonItemResult<HomeProductCardChildItem>();
        result.setViewType("product_card");
        result.setBody(new HomeProductCardChildItem());
        return result;
    }
}
