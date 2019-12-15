package com.m4coding.mallmanager.controller;

import com.m4coding.mallbase.api.CommonPage;
import com.m4coding.mallbase.api.CommonResult;
import com.m4coding.mallbase.version.ApiVersion;
import com.m4coding.mallmanager.dto.*;
import com.m4coding.mallmanager.service.PmsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "PmsProductController", description = "商品管理")
@RestController
@RequestMapping("/api/product/{version}")
public class PmsProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsProductController.class);

    @Autowired
    private PmsProductService pmsProductService;

    @ApiOperation(value = "商品创建")
    @ApiVersion(1)
    @PostMapping("/create")
    public CommonResult create(@ApiParam(value = "版本号", allowableValues = "v1", required = true)
                               @PathVariable(value = "version") String version,
                               @Valid @RequestBody PmsProductParam pmsProductParam, BindingResult bindingResult) {
        try {
            if (pmsProductService.create(pmsProductParam) != 1) {
                return CommonResult.failed("商品创建异常");
            }
        } catch (Exception e) {
            LOGGER.error("商品创建异常:{}", e.getMessage(), e);
            return CommonResult.failed("商品创建异常");
        }

        return CommonResult.success(null);
    }

    @ApiOperation(value = "商品更新")
    @ApiVersion(1)
    @PostMapping("/update")
    public CommonResult update(@ApiParam(value = "版本号", allowableValues = "v1", required = true)
                               @PathVariable(value = "version") String version,
                               @Valid @RequestBody PmsProductUpdateParam pmsProductUpdateParam, BindingResult bindingResult) {
        try {
            if (pmsProductService.update(pmsProductUpdateParam) != 1) {
                return CommonResult.failed("商品更新异常");
            }
        } catch (Exception e) {
            LOGGER.error("商品更新异常:{}", e.getMessage(), e);
            return CommonResult.failed("商品更新异常");
        }

        return CommonResult.success(null);
    }

    @ApiOperation(value = "批量更新spu状态")
    @ApiVersion(1)
    @PostMapping("/updateSpuStatus")
    public CommonResult updateSpuStatus(@ApiParam(value = "版本号", allowableValues = "v1", required = true)
                                        @PathVariable(value = "version") String version,
                                        @Valid @RequestBody PmsProductStatusParam pmsProductStatusParam, BindingResult bindingResult) {
        try {
            if (pmsProductService.batchUpdateSpuStatus(pmsProductStatusParam.getIds(), pmsProductStatusParam.getStatus()) != 1) {
                LOGGER.error("spu更新状态异常:{}", "pmsProductService.batchUpdateSpuStatus error ids="
                        + pmsProductStatusParam.getIds() + " status=" + pmsProductStatusParam.getStatus());
                return CommonResult.failed("spu更新状态异常");
            }
        } catch (Exception e) {
            LOGGER.error("spu更新状态异常:{}", e.getMessage(), e);
            return CommonResult.failed("spu更新状态异常");
        }

        return CommonResult.success(null);
    }


    @ApiOperation(value = "批量更新sku状态")
    @ApiVersion(1)
    @PostMapping("/updateSkuStatus")
    public CommonResult updateSkuStatus(@ApiParam(value = "版本号", allowableValues = "v1", required = true)
                                        @PathVariable(value = "version") String version,
                                        @Valid @RequestBody PmsProductStatusParam pmsProductStatusParam, BindingResult bindingResult) {
        try {
            if (pmsProductService.batchUpdateSkuStatus(pmsProductStatusParam.getIds(), pmsProductStatusParam.getStatus()) != 1) {
                LOGGER.error("sku更新状态异常:{}", "pmsProductService.batchUpdateSkuStatus error ids="
                        + pmsProductStatusParam.getIds() + " status=" + pmsProductStatusParam.getStatus());
                return CommonResult.failed("sku更新状态异常");
            }
        } catch (Exception e) {
            LOGGER.error("sku更新状态异常:{}", e.getMessage(), e);
            return CommonResult.failed("sku更新状态异常");
        }

        return CommonResult.success(null);
    }

    @ApiOperation(value = "查询商品，分页查询")
    @ApiVersion(1)
    @PostMapping("/getProductList")
    public CommonResult<CommonPage<ListProductResult>> getList(@ApiParam(value = "版本号", allowableValues = "v1", required = true)
                                                               @PathVariable(value = "version") String version,
                                                               @Valid @RequestBody PmsProductQueryParam pmsProductQueryParam, BindingResult bindingResult) {
        List<ListProductResult> list = pmsProductService.getList(pmsProductQueryParam,
                pmsProductQueryParam.getPageSize(), pmsProductQueryParam.getPageNum());
        return CommonResult.success(CommonPage.restPage(list));
    }
}
