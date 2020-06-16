package com.m4coding.mallforeground.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * 首页商品列表查询参数
 */
public class HomeProductListQueryParam {

    @ApiModelProperty(value = "每页数目", required = true)
    @NotNull(message = "每页数目不能为空")
    @Positive(message = "每页数目不能小于0")
    private Integer pageSize;

    @ApiModelProperty(value = "页码", required = true)
    @NotNull(message = "页码不能为空")
    @Positive(message = "页码不能小于0")
    private Integer pageNum;

    @ApiModelProperty(value = "tab类型 -2000值指定为推荐获取，其他值也即是分类ID", required = false)
    private String tabType;

    @ApiModelProperty(value = "关键字", required = false)
    private String keyword;

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

    public String getTabType() {
        return tabType;
    }

    public void setTabType(String tabType) {
        this.tabType = tabType;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
