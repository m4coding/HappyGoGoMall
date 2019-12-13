package com.m4coding.mallmanager.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品相关入参
 */
public class PmsProductParam {

    @ApiModelProperty(value = "商品名称", required = true)
    @NotEmpty(message = "商品名称不能为空")
    private String productName;

    @ApiModelProperty(value = "分类id", required = true)
    @Positive(message = "无效的分类id")
    @NotNull(message = "分类不能为空")
    private Long categoryId;

    @ApiModelProperty(value = "品牌id", required = true)
    @Positive(message = "无效的品牌id")
    @NotNull(message = "品牌不能为空")
    private Long brandId;

    @ApiModelProperty(value = "商品描述", required = true)
    @NotEmpty(message = "商品描述不能为空")
    private String description;

    @ApiModelProperty(value = "库存", required = true)
    @PositiveOrZero(message = "库存不能小于0")
    @NotNull(message = "库存数不能为空")
    private Long stock;

    @ApiModelProperty(value = "市场价", required = true)
    @PositiveOrZero(message = "市场价不能小于0")
    @NotNull(message = "市场价不能为空")
    private BigDecimal marketPrice;

    @ApiModelProperty(value = "销售价", required = true)
    @PositiveOrZero(message = "销售价不能小于0")
    @NotNull(message = "销售价不能为空")
    private BigDecimal salePrice;

    @ApiModelProperty(value = "商品主介绍图")
    private List<String> mainPicList;

    @ApiModelProperty(value = "商品广告图")
    private List<String> bannerPicList;

    @ApiModelProperty(value = "属性值list")
    private List<Attrs> attrs = new ArrayList<>();

    public static class Attrs {

        @ApiModelProperty(value = "属性id")
        private Long attrKeyId;

        @ApiModelProperty(value = "属性")
        private String attrsKey;

        @ApiModelProperty(value = "属性描述")
        private String attrKeyDescription;

        @ApiModelProperty(value = "属性值")
        private String attrValue;

        @ApiModelProperty(value = "属性值id")
        private Long attrValueId;

        @ApiModelProperty(value = "属性值描述")
        private String attrValueDescription;

        public String getAttrsKey() {
            return attrsKey;
        }

        public void setAttrsKey(String attrsKey) {
            this.attrsKey = attrsKey;
        }

        public String getAttrValue() {
            return attrValue;
        }

        public void setAttrValue(String attrValue) {
            this.attrValue = attrValue;
        }

        public String getAttrKeyDescription() {
            return attrKeyDescription;
        }

        public void setAttrKeyDescription(String attrKeyDescription) {
            this.attrKeyDescription = attrKeyDescription;
        }

        public String getAttrValueDescription() {
            return attrValueDescription;
        }

        public void setAttrValueDescription(String attrValueDescription) {
            this.attrValueDescription = attrValueDescription;
        }

        public Long getAttrKeyId() {
            return attrKeyId;
        }

        public void setAttrKeyId(Long attrKeyId) {
            this.attrKeyId = attrKeyId;
        }

        public Long getAttrValueId() {
            return attrValueId;
        }

        public void setAttrValueId(Long attrValueId) {
            this.attrValueId = attrValueId;
        }
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

    public List<Attrs> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<Attrs> attrs) {
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
