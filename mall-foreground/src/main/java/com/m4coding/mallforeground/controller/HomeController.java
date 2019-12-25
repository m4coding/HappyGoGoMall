package com.m4coding.mallforeground.controller;

import com.m4coding.mallbase.api.CommonPage;
import com.m4coding.mallbase.api.CommonResult;
import com.m4coding.mallbase.version.ApiVersion;
import com.m4coding.mallforeground.dto.HomeProductCategoryQueryParam;
import com.m4coding.mallforeground.dto.HomeProductCategoryResult;
import com.m4coding.mallforeground.dto.HomeProductListQueryParam;
import com.m4coding.mallforeground.service.HomePageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 首页相关的控制器
 */
@Api(tags = "HomeController", description = "首页相关")
@RestController
@RequestMapping("api/home/{version}")
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private HomePageService homePageService;

    @ApiOperation(value = "获取首页顶部信息列表")
    @ApiVersion(1)
    @PostMapping(value = "/pageContentInfo")
    public CommonResult pageContentInfo(@ApiParam(value = "版本号", allowableValues = "v1", required = true)
                              @PathVariable(value = "version") String version) {
       return CommonResult.success(homePageService.pageContentInfo());
    }


    @ApiOperation(value = "获取首页商品列表")
    @ApiVersion(1)
    @PostMapping(value = "/pageListInfo")
    public CommonResult pageListInfo(@ApiParam(value = "版本号", allowableValues = "v1", required = true)
                                        @PathVariable(value = "version") String version,
                                     @Valid @RequestBody HomeProductListQueryParam homeProductListQueryParam, BindingResult bindingResult) {
        return CommonResult.success(homePageService.pageListInfo(homeProductListQueryParam.getPageSize(), homeProductListQueryParam.getPageNum()));
    }


    @ApiOperation(value = "查询商品分类，分页查询")
    @ApiVersion(1)
    @PostMapping("/getCategoryList")
    public CommonResult<CommonPage<HomeProductCategoryResult>> getCategoryList(@ApiParam(value = "版本号", allowableValues = "v1", required = true)
                                                                               @PathVariable(value = "version") String version,
                                                                               @Valid @RequestBody HomeProductCategoryQueryParam homeProductCategoryQueryParam, BindingResult bindingResult) {
        CommonPage<HomeProductCategoryResult> list = homePageService.getProductCategoryList(homeProductCategoryQueryParam,
                homeProductCategoryQueryParam.getPageSize(), homeProductCategoryQueryParam.getPageNum());
        return CommonResult.success(list);
    }
}
