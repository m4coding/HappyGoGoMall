package com.m4coding.mallmanager.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品相关入参
 */
public class PmsProductUpdateParam {

    @ApiModelProperty(value = "要更新的spu id")
    private Long productId;
    @ApiModelProperty(value = "要更新的sku id")
    private Long skuId;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "分类id")
    @Positive(message = "无效的分类id")
    private Long categoryId;

    @ApiModelProperty(value = "品牌id")
    @Positive(message = "无效的品牌id")
    private Long brandId;

    @ApiModelProperty(value = "商品描述")
    private String description;

    @ApiModelProperty(value = "库存")
    @PositiveOrZero(message = "库存不能小于0")
    private Long stock;

    @ApiModelProperty(value = "市场价")
    @PositiveOrZero(message = "市场价不能小于0")
    private BigDecimal marketPrice;

    @ApiModelProperty(value = "销售价")
    @PositiveOrZero(message = "销售价不能小于0")
    private BigDecimal salePrice;

    @ApiModelProperty(value = "商品主介绍图")
    private List<String> mainPicList;

    @ApiModelProperty(value = "商品广告图")
    private List<String> bannerPicList;

    @ApiModelProperty(value = "属性值list")
    private List<PmsProductParam.Attrs> attrs = new ArrayList<>();

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public List<PmsProductParam.Attrs> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<PmsProductParam.Attrs> attrs) {
        this.attrs = attrs;
    }

    public List<String> getMainPicList() {
        return mainPicList;
    }

    public void setMainPicList(List<String> mainPicList) {
        this.mainPicList = mainPicList;
    }

    public List<String> getBannerPicList() {
        return bannerPicList;
    }

    public void setBannerPicList(List<String> bannerPicList) {
        this.bannerPicList = bannerPicList;
    }
}
