package com.m4coding.mallforeground.dto.product;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 商品详情结果
 */
public class ProductDetailsResult {
    @ApiModelProperty(value = "商品图片")
    private List<String> imageUrls;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品原始价格")
    private String productOrgPrice;

    @ApiModelProperty(value = "商品销售价格")
    private String productPrice;

    @ApiModelProperty(value = "商品Id")
    private Long productSkuId;

    @ApiModelProperty(value = "spu Id")
    private Long productSpuId;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    @ApiModelProperty(value = "分类名称")
    private String category;

    @ApiModelProperty(value = "品牌名称")
    private String brand;

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
