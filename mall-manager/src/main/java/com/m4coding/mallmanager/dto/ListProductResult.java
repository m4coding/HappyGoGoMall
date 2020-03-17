package com.m4coding.mallmanager.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 列表中的商品结果
 */
public class ListProductResult {
    @ApiModelProperty(value = "名牌名称")
    private String brandName;

    @ApiModelProperty(value = "种类名称")
    private String categoryName;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "市场价")
    private BigDecimal marketPrice;

    @ApiModelProperty(value = "销售价")
    private BigDecimal salePrice;

    @ApiModelProperty(value = "库存量")
    private Long stock;

    @ApiModelProperty(value = "商品广告图")
    private List<String> bannerPicList;

    @ApiModelProperty(value = "商品Id")
    private Long productSkuId;

    @ApiModelProperty(value = "spu Id")
    private Long productSpuId;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public List<String> getBannerPicList() {
        return bannerPicList;
    }

    public void setBannerPicList(List<String> bannerPicList) {
        this.bannerPicList = bannerPicList;
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
