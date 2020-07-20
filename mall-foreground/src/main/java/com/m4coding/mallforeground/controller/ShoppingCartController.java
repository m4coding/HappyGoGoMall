package com.m4coding.mallforeground.controller;

import com.m4coding.mallbase.api.CommonResult;
import com.m4coding.mallbase.version.ApiVersion;
import com.m4coding.mallforeground.dto.*;
import com.m4coding.mallforeground.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 购物车管理控制器
 */
@Api(tags = "ShoppingCartController", description = "购物车相关")
@RestController
@RequestMapping("api/cart/{version}")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @ApiOperation(value = "加入购物车")
    @ApiVersion(1)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public CommonResult add(@ApiParam(value = "版本号", allowableValues = "v1", required = true)
                                          @PathVariable(value = "version") String version,
                                          @Valid @RequestBody OmsCartAddParam omsCartAddParam,
                                          BindingResult bindingResult) {
        boolean isSuccess;
        try {
            isSuccess = shoppingCartService.addCart(omsCartAddParam);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }

        if (!isSuccess) {
            return CommonResult.failed("加入购物车失败");
        }

        return CommonResult.success(null, "加入购物车成功");
    }

    @ApiOperation(value = "从购物车删除商品")
    @ApiVersion(1)
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult delete(@ApiParam(value = "版本号", allowableValues = "v1", required = true)
                                          @PathVariable(value = "version") String version,
                                          @Valid @RequestBody OmsCartDeleteParam omsCartDeleteParam,
                                          BindingResult bindingResult) {
        boolean isSuccess;
        try {
            isSuccess = shoppingCartService.deleteInCart(omsCartDeleteParam);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }

        if (!isSuccess) {
            return CommonResult.failed();
        }

        return CommonResult.success(null, "删除成功");
    }

    @ApiOperation(value = "获取购物车信息")
    @ApiVersion(1)
    @RequestMapping(value = "/getCartInfo", method = RequestMethod.POST)
    public CommonResult<OmsCartInfoResult> getCartInfo(@ApiParam(value = "版本号", allowableValues = "v1", required = true)
                               @PathVariable(value = "version") String version,
                                                       @Valid @RequestBody OmsCartListQueryParam omsCartListQueryParam,
                                                       BindingResult bindingResult) {
        OmsCartInfoResult omsCartInfoResult;
        try {
            omsCartInfoResult = shoppingCartService.getCartInfo();
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }

        if (omsCartInfoResult == null) {
            return CommonResult.failed();
        }

        return CommonResult.success(omsCartInfoResult, "获取购物车信息成功");
    }


    @ApiOperation(value = "更新购物车信息")
    @ApiVersion(1)
    @RequestMapping(value = "/updateCartInfo", method = RequestMethod.POST)
    public CommonResult updateCartInfo(@ApiParam(value = "版本号", allowableValues = "v1", required = true)
                                                       @PathVariable(value = "version") String version,
                                                       @Valid @RequestBody OmsCartUpdateParam omsCartUpdateParam,
                                                       BindingResult bindingResult) {
        boolean isSuccess;
        try {
            isSuccess = shoppingCartService.updateCartInfo(omsCartUpdateParam);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }

        if (!isSuccess) {
            return CommonResult.failed();
        }

        return CommonResult.success(null, "更新购物车信息成功");
    }

    @ApiOperation(value = "获取购物车商品种类数量")
    @ApiVersion(1)
    @RequestMapping(value = "/getCartCount", method = RequestMethod.POST)
    public CommonResult<Integer> getCartCount(@ApiParam(value = "版本号", allowableValues = "v1", required = true)
                                                       @PathVariable(value = "version") String version) {
        Integer count = 0;
        try {
            count = shoppingCartService.getCartCount();
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }

        if (count == null) {
            return CommonResult.failed();
        }

        return CommonResult.success(count, "获取购物车商品种类数量成功");
    }
}
