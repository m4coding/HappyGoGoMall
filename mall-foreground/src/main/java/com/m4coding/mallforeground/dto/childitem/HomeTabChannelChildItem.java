package com.m4coding.mallforeground.dto.childitem;

import com.m4coding.mallforeground.dto.HomeCommonItemResult;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * viewtype:tab_channel
 */
@Setter
@Getter
public class HomeTabChannelChildItem {
    private List<Child> items;
    private String selectedTextColor;
    private String tabBgColor;

    @Setter
    @Getter
    public static class Child {
        private String title;
        private String subTitle;
        private String type;
    }

    public static HomeCommonItemResult<HomeTabChannelChildItem> createCommonItem() {
        HomeCommonItemResult result = new HomeCommonItemResult<HomeTabChannelChildItem>();
        result.setViewType("tab_channel");
        result.setBody(new HomeTabChannelChildItem());
        return result;
    }
}
