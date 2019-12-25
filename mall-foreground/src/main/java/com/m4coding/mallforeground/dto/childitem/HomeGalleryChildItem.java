package com.m4coding.mallforeground.dto.childitem;

import com.m4coding.mallforeground.dto.HomeCommonItemResult;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * viewType: gallery
 */
@Setter
@Getter
public class HomeGalleryChildItem {
    private List<Child> items;

    @Setter
    @Getter
    public static class Child {
        private String imageUrl;
        private String actionUrl;
    }

    public static HomeCommonItemResult<HomeGalleryChildItem> createCommonItem() {
        HomeCommonItemResult result = new HomeCommonItemResult<HomeGalleryChildItem>();
        result.setViewType("gallery");
        result.setBody(new HomeGalleryChildItem());
        return result;
    }
}
