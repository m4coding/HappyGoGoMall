package com.m4coding.mallforeground.dto.childitem;

import com.m4coding.mallforeground.dto.HomeCommonItemResult;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * viewType: product_card
 */
@Setter
@Getter
public class HomeProductCardChildItem {
    private List<Child> items;

    @Setter
    @Getter
    public static class Child {
        private String imageUrl;
        private String productName;
        private String productOrgPrice;
        private String productPrice;
    }

    public static HomeCommonItemResult<HomeProductCardChildItem> createItem() {
        HomeCommonItemResult result = new HomeCommonItemResult<HomeProductCardChildItem>();
        result.setViewType("gallery");
        result.setBody(new HomeProductCardChildItem());
        return result;
    }
}
