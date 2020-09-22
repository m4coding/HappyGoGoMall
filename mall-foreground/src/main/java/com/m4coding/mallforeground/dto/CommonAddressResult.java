package com.m4coding.mallforeground.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 通用地址模型
 */
public class CommonAddressResult {

    //地区类型 1：省（包括直辖市）、2：城市、3：区、4：街道
    public static final int TYPE_PROVINCE = 1;
    public static final int TYPE_CITY = 2;
    public static final int TYPE_REGION = 3;
    public static final int TYPE_STREET = 4;

    @ApiModelProperty(value = "地区名称")
    private String name;
    @ApiModelProperty(value = "地区id")
    private String id;
    @ApiModelProperty(value = "地区类型 1：省（包括直辖市）、2：城市、3：区、4：街道")
    private int type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
