package com.m4coding.mallmbg.bean;

import com.m4coding.mallmbg.mbg.model.PmsCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类数据bean
 */
public class CategoryBean extends PmsCategory {

     private List<CategoryBean> childList = new ArrayList<>();

    public CategoryBean(String name, long createTime, long updateTime) {
        setCategoryName(name);
        setCreateTime((int) createTime);
        setUpdateTime((int) updateTime);
    }

    public CategoryBean add(CategoryBean childCategoryBean) {
        if (childCategoryBean != null) {
            childList.add(childCategoryBean);
        }

        return this;
    }

    public List<CategoryBean> getChildList() {
        return childList;
    }

    public void setChildList(List<CategoryBean> childList) {
        this.childList = childList;
    }
}
