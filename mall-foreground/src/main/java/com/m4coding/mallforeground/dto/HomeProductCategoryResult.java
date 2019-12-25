package com.m4coding.mallforeground.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 商品分类结果
 */
@Setter
@Getter
public class HomeProductCategoryResult {
    @ApiModelProperty(value = "商品分类名称")
    private String name;
    @ApiModelProperty(value = "分类id")
    private Long categoryId;
    @ApiModelProperty(value = "子分类列表")
    private List<HomeProductCategoryResult> childCategoryList;
}
