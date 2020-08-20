package com.m4coding.mallforeground.controller;

import com.m4coding.mallbase.api.CommonResult;
import com.m4coding.mallbase.version.ApiVersion;
import com.m4coding.mallforeground.dto.*;
import com.m4coding.mallforeground.service.OmsOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 前台订单管理控制器
 */
@Api(tags = "OrderController", description = "订单相关")
@RestController
@RequestMapping("api/order/{version}")
public class OrderController {

    @Autowired
    private OmsOrderService omsOrderService;

    @ApiOperation(value = "获取确认订单信息")
    @ApiVersion(1)
    @RequestMapping(value = "/getConfirmOrderInfo", method = RequestMethod.POST)
    public CommonResult<OmsOrderConfirmResult> getConfirmOrderInfo(@ApiParam(value = "版本号", allowableValues = "v1", required = true)
                                                       @PathVariable(value = "version") String version,
                                                       @Valid @RequestBody OmsOrderConfirmParam omsOrderConfirmParam,
                                                       BindingResult bindingResult) {
        OmsOrderConfirmResult omsOrderConfirmResult;
        try {
            omsOrderConfirmResult = omsOrderService.orderConfirmInfo(omsOrderConfirmParam);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }

        if (omsOrderConfirmResult == null) {
            return CommonResult.failed();
        }

        return CommonResult.success(omsOrderConfirmResult, "获取确认订单信息成功");
    }


    @ApiOperation(value = "下单")
    @ApiVersion(1)
    @RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
    public CommonResult<OmsOrderPlaceResult> placeOrder(@ApiParam(value = "版本号", allowableValues = "v1", required = true)
                                                                   @PathVariable(value = "version") String version,
                                                                   @Valid @RequestBody OmsOrderPlaceParam omsOrderPlaceParam,
                                                                   BindingResult bindingResult) {
        OmsOrderPlaceResult omsOrderPlaceResult;
        try {
            omsOrderPlaceResult = omsOrderService.placeOrder(omsOrderPlaceParam);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }

        if (omsOrderPlaceResult == null) {
            return CommonResult.failed();
        }

        return CommonResult.success(omsOrderPlaceResult, "下单成功");
    }
}
