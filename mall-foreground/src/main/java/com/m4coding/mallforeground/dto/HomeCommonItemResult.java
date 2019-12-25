package com.m4coding.mallforeground.dto;

/**
 * 首页通用的数据item
 * @param <T>
 */
public class HomeCommonItemResult<T> {
    private String viewType;
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
