package com.m4coding.mallforeground.dto.childitem;

import lombok.Getter;
import lombok.Setter;

/**
 * viewType:cells_auto_fill
 */
@Getter
@Setter
public class HomeCellAutoFillChildItem {
    private int w;
    private int h;
    private String bgColor;

    @Getter
    @Setter
    public static class Child {
        private String imageUrl;
        private int w;
        private int h;
    }
}
