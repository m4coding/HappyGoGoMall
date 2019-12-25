package com.m4coding.mallforeground.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * 首页商品列表查询参数
 */
@Setter
@Getter
public class HomeProductListQueryParam {

    @ApiModelProperty(value = "每页数目", required = true)
    @NotNull(message = "每页数目不能为空")
    @Positive(message = "每页数目不能小于0")
    private Integer pageSize;

    @ApiModelProperty(value = "页码", required = true)
    @NotNull(message = "页码不能为空")
    @Positive(message = "页码不能小于0")
    private Integer pageNum;
}
