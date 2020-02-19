package com.m4coding.mallforeground.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 首页通用的数据item
 * @param <T>
 */
public class HomeCommonItemResult<T> {
    @ApiModelProperty(value = "view类型")
    private String viewType;
    @ApiModelProperty(value = "view的实际数据")
    private T body;

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
