package com.m4coding.mallforeground.controller;

import com.m4coding.mallbase.api.CommonResult;
import com.m4coding.mallbase.version.ApiVersion;
import com.m4coding.mallforeground.dto.product.ProductDetailsParam;
import com.m4coding.mallforeground.dto.product.ProductDetailsResult;
import com.m4coding.mallforeground.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 商品控制器
 */
@Api(tags = "ProductController", description = "商品相关")
@RestController
@RequestMapping("api/product/{version}")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "获取商品详情信息")
    @ApiVersion(1)
    @RequestMapping(value = "/getProductDetail", method = RequestMethod.POST)
    public CommonResult<ProductDetailsResult> getCartInfo(@ApiParam(value = "版本号", allowableValues = "v1", required = true)
                                                       @PathVariable(value = "version") String version,
                                                       @Valid @RequestBody ProductDetailsParam productDetailsParam,
                                                       BindingResult bindingResult) {
        ProductDetailsResult productDetailsResult;
        try {
            productDetailsResult = productService.getProductDetails(productDetailsParam);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }

        if (productDetailsResult == null) {
            return CommonResult.failed();
        }

        return CommonResult.success(productDetailsResult, "获取商品详情信息成功");
    }
}
