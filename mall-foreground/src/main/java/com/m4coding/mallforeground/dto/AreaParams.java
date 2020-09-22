package com.m4coding.mallforeground.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 * 区域入参
 *
 * 区域码划分规则
 *
 * 第一、二位表示省（自治区、直辖市、特别行政区）。
 *
 * 第三、四位表示市（地区、自治州、盟及国家直辖市所属市辖区和县的汇总码）。其中，01-20，51-70表示省直辖市；21-50表示地区（自治州、盟）。
 *
 * 第五、六位表示县（市辖区、县级市、旗）。01-18表示市辖区或地区（自治州、盟）辖县级市；21-80表示县（旗）；81-99表示省直辖县级市。
 *
 * 第七至九位表示乡、镇（街道办事处）
 */
@ApiModel(description = "区域入参信息")
public class AreaParams {

    @ApiModelProperty(value = "区域码， 若为空表示获取的是省列表")
    private String areaCode;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
}
