package com.m4coding.mallmbg.bean;

import com.m4coding.mallmbg.mbg.model.PmsBrand;

public class BrandBean extends PmsBrand {

    public BrandBean(String brandName, long createTime, long updateTime) {
        this.setBrandName(brandName);
        this.setCreateTime((int) createTime);
        this.setUpdateTime((int) updateTime);
    }
}
