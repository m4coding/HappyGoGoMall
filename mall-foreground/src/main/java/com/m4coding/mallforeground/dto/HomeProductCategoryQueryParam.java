package com.m4coding.mallforeground.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * 商品分类查询参数
 */
public class HomeProductCategoryQueryParam {
    @ApiModelProperty(value = "商品分类模糊关键字,不填或者空字符串则表示搜索全部")
    private String keyword;

    @ApiModelProperty(value = "每页数目", required = true)
    @NotNull(message = "每页数目不能为空")
    @Positive(message = "每页数目不能小于0")
    private Integer pageSize;

    @ApiModelProperty(value = "页码", required = true)
    @NotNull(message = "页码不能为空")
    @Positive(message = "页码不能小于0")
    private Integer pageNum;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
