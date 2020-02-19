package com.m4coding.mallforeground.dto;

import com.m4coding.mallforeground.dto.childitem.HomeTabChannelChildItem;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 首页返回结果信息
 */
@Setter
@Getter
public class HomePageInfoResult {

//    private HomeCommonItemResult<HomeTabChannelChildItem> tabChannel;
    @ApiModelProperty(value = "列表数据")
    private List<HomeCommonItemResult> sections;
}
