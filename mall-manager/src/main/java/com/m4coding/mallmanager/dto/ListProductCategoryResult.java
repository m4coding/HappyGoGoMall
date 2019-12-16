package com.m4coding.mallmanager.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 商品分类结果
 */
public class ListProductCategoryResult {
    @ApiModelProperty(value = "商品分类名称")
    private String name;
    @ApiModelProperty(value = "分类id")
    private Long categoryId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
