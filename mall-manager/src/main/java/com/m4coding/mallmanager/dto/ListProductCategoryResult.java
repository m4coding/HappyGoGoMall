package com.m4coding.mallmanager.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 商品分类结果
 */
public class ListProductCategoryResult {
    @ApiModelProperty(value = "商品分类名称")
    private String name;
    @ApiModelProperty(value = "分类id")
    private Long categoryId;
    @ApiModelProperty(value = "子分类列表")
    private List<ListProductCategoryResult> childCategoryList;

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

    public List<ListProductCategoryResult> getChildCategoryList() {
        return childCategoryList;
    }

    public void setChildCategoryList(List<ListProductCategoryResult> childCategoryList) {
        this.childCategoryList = childCategoryList;
    }
}
