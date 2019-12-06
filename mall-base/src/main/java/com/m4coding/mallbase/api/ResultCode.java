package com.m4coding.mallbase.api;

/**
 * 枚举了一些常用API操作码
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(20000, "操作成功"),
    FAILED(20500, "操作失败"),
    VALIDATE_FAILED(20404, "参数检验失败"),
    UNAUTHORIZED(20401, "暂未登录或token已经过期"),
    FORBIDDEN(20403, "没有相关权限");

    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
