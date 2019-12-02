package com.m4coding.mallforeground.mbg.model;

import java.io.Serializable;
import java.util.Date;

public class Products implements Serializable {
    /**
     * 商品ID
     *
     * @mbg.generated
     */
    private Integer productId;

    /**
     * 商品名称
     *
     * @mbg.generated
     */
    private String productName;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * 商品采购价
     *
     * @mbg.generated
     */
    private Long purchasePrice;

    /**
     * 市场价格
     *
     * @mbg.generated
     */
    private Long marketPrice;

    /**
     * 销售价格
     *
     * @mbg.generated
     */
    private Long salePrice;

    /**
     * 类别ID
     *
     * @mbg.generated
     */
    private Integer categoryId;

    /**
     * 商品描述
     *
     * @mbg.generated
     */
    private String description;

    /**
     * 商品图片
     *
     * @mbg.generated
     */
    private String icon;

    private static final long serialVersionUID = 1L;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Long purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Long getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Long marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Long salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", productId=").append(productId);
        sb.append(", productName=").append(productName);
        sb.append(", createTime=").append(createTime);
        sb.append(", purchasePrice=").append(purchasePrice);
        sb.append(", marketPrice=").append(marketPrice);
        sb.append(", salePrice=").append(salePrice);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", description=").append(description);
        sb.append(", icon=").append(icon);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}