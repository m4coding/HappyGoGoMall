package com.m4coding.mallforeground.dto;

import com.m4coding.mallforeground.dto.childitem.HomeTabChannelChildItem;
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
    private List<HomeCommonItemResult> sections;
}
