package com.m4coding.mallmanager.controller;

import com.m4coding.mallbase.api.CommonPage;
import com.m4coding.mallbase.api.CommonResult;
import com.m4coding.mallbase.version.ApiVersion;
import com.m4coding.mallmanager.dto.*;
import com.m4coding.mallmanager.service.OmsOrderService;
import com.m4coding.mallmbg.mbg.model.OmsOrder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "OmsOrderController", description = "订单相关")
@RestController
@RequestMapping("/api/order/{version}")
public class OmsOrderController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OmsOrderController.class);

    @Autowired
    private OmsOrderService omsOrderService;

    @ApiOperation(value = "获取订单列表")
    @ApiVersion(1)
    @PostMapping("/getList")
    public CommonResult<CommonPage<OmsOrder>> getList(@ApiParam(value = "版本号", allowableValues = "v1", required = true)
                                                          @PathVariable(value = "version") String version,
                                                      @Valid @RequestBody OmsOrderQueryParam omsOrderQueryParam, BindingResult bindingResult) {
        CommonPage<OmsOrder> list = omsOrderService.getList(omsOrderQueryParam);
        return CommonResult.success(list);
    }


    @ApiOperation(value = "批量发货")
    @ApiVersion(1)
    @PostMapping("/delivery")
    public CommonResult delivery(@ApiParam(value = "版本号", allowableValues = "v1", required = true)
                                     @PathVariable(value = "version") String version,
                                 @RequestBody List<OmsOrderDeliveryParam> deliveryParamList) {
        int count = omsOrderService.delivery(deliveryParamList);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    @ApiOperation(value = "批量关闭订单")
    @ApiVersion(1)
    @PostMapping("/close")
    public CommonResult close(@ApiParam(value = "版本号", allowableValues = "v1", required = true)
                                  @PathVariable(value = "version") String version,
                              @RequestParam("ids") List<Long> ids, @RequestParam String note) {
        int count = omsOrderService.close(ids, note);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

//    /**
//     * 批量删除订单
//     */
//    int delete(List<Long> ids);
//
//    /**
//     * 获取指定订单详情
//     */
//    OmsOrderDetail getDetail(Long id);
//
//    /**
//     * 修改订单收货人信息
//     */
//    @Transactional
//    int updateReceiverInfo(OmsReceiverInfoParam receiverInfoParam);
//
//
//    /**
//     * 修改订单备注
//     */
//    @Transactional
//    int updateNote(Long id, String note, Integer status);
}
