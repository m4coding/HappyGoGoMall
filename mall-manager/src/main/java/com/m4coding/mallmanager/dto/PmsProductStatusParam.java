package com.m4coding.mallmanager.dto;

import com.m4coding.mallbase.validator.FlagValidator;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 产品状态相关入参
 */
public class PmsProductStatusParam {
    @ApiModelProperty(value = "要更新的id集合", required = true)
    @NotNull(message = "id集合不能为空")
    private List<Long> ids;

    @ApiModelProperty(value = "更新状态 1:enable, 0:disable, -1:deleted", required = true)
    @NotNull(message = "更新状态不能为空")
    @FlagValidator(value = {"-1", "0", "1"}, message = "更新状态值不在允许范围内")
    private Integer status;


    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
