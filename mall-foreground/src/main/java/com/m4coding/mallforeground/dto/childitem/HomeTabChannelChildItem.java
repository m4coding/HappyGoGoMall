package com.m4coding.mallforeground.dto.childitem;

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
}
