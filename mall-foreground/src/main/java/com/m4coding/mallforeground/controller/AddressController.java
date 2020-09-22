package com.m4coding.mallforeground.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.m4coding.mallbase.api.CommonResult;
import com.m4coding.mallbase.version.ApiVersion;
import com.m4coding.mallforeground.dto.*;
import com.m4coding.mallforeground.service.UmsUserReceiverAddressService;
import com.m4coding.mallmbg.mbg.model.UmsUserReceiverAddress;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 地址管理控制器
 */
@Api(tags = "AddressController", description = "地址管理相关")
@RestController
@RequestMapping("api/address/{version}")
public class AddressController {

    @Autowired
    private UmsUserReceiverAddressService receiverAddressService;

    @ApiOperation(value = "添加新地址")
    @ApiVersion(1)
    @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    public CommonResult<UmsUserReceiverAddressListResult.AddressBean> add(@ApiParam(value = "版本号", allowableValues = "v1", required = true)
                            @PathVariable(value = "version") String version,
                                                                          @Valid @RequestBody UmsUserReceiverAddressParam umsUserReceiverAddressParam,
                                                                          BindingResult bindingResult) {
        boolean isSuccess;
        UmsUserReceiverAddress umsUserReceiverAddress = new UmsUserReceiverAddress();
        try {
            umsUserReceiverAddress.setPhoneNumber(umsUserReceiverAddressParam.getReceiverPhone());
            umsUserReceiverAddress.setName(umsUserReceiverAddressParam.getReceiverName());
            umsUserReceiverAddress.setRegion(umsUserReceiverAddressParam.getReceiverRegion());
            umsUserReceiverAddress.setDetailAddress(umsUserReceiverAddressParam.getReceiverAddr());
            umsUserReceiverAddress.setDefaultStatus(umsUserReceiverAddressParam.getIsDefault());
            isSuccess = receiverAddressService.add(umsUserReceiverAddress) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }

        if (!isSuccess) {
            return CommonResult.failed("添加地址失败");
        }

        UmsUserReceiverAddressListResult.AddressBean addressBean = new UmsUserReceiverAddressListResult.AddressBean();
        addressBean.setIsDefault(umsUserReceiverAddress.getDefaultStatus());
        addressBean.setReceiverAddr(umsUserReceiverAddress.getDetailAddress());
        addressBean.setReceiverName(umsUserReceiverAddress.getName());
        addressBean.setReceiverPhone(umsUserReceiverAddress.getPhoneNumber());
        addressBean.setReceiverRegion(umsUserReceiverAddress.getRegion());
        addressBean.setAddressId(umsUserReceiverAddress.getId().toString());

        return CommonResult.success(addressBean, "添加地址成功");
    }

    @ApiOperation(value = "删除地址")
    @ApiVersion(1)
    @RequestMapping(value = "/deleteAddress", method = RequestMethod.POST)
    public CommonResult delete(@ApiParam(value = "版本号", allowableValues = "v1", required = true)
                               @PathVariable(value = "version") String version,
                               @Valid @RequestBody UmsUserReceiverAddressDeleteParam deleteParam,
                               BindingResult bindingResult) {
        boolean isSuccess;
        try {
            isSuccess = receiverAddressService.delete(deleteParam.getAddressId()) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }

        if (!isSuccess) {
            return CommonResult.failed();
        }

        return CommonResult.success(null, "删除成功");
    }


    @ApiOperation(value = "获取我的地址列表信息")
    @ApiVersion(1)
    @RequestMapping(value = "/getMyAddressList", method = RequestMethod.POST)
    public CommonResult<UmsUserReceiverAddressListResult> getMyAddressList(@ApiParam(value = "版本号", allowableValues = "v1", required = true)
                                                       @PathVariable(value = "version") String version) {

        UmsUserReceiverAddressListResult addressListResult = new UmsUserReceiverAddressListResult();
        try {
            List<UmsUserReceiverAddress> list = receiverAddressService.list();
            if (CollectionUtil.isNotEmpty(list)) {
                List<UmsUserReceiverAddressListResult.AddressBean> addressBeanList = new ArrayList<>();
                for (UmsUserReceiverAddress receiverAddress : list) {
                    UmsUserReceiverAddressListResult.AddressBean addressBean =
                            new UmsUserReceiverAddressListResult.AddressBean();
                    addressBean.setIsDefault(receiverAddress.getDefaultStatus());
                    addressBean.setReceiverAddr(receiverAddress.getDetailAddress());
                    addressBean.setReceiverName(receiverAddress.getName());
                    addressBean.setReceiverPhone(receiverAddress.getPhoneNumber());
                    addressBean.setReceiverRegion(receiverAddress.getRegion());
                    addressBean.setAddressId(receiverAddress.getId().toString());
                    addressBeanList.add(addressBean);
                }

                addressListResult.setAddressList(addressBeanList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }

        return CommonResult.success(addressListResult, "获取我的地址列表信息成功");
    }

    @ApiOperation(value = "编辑地址")
    @ApiVersion(1)
    @RequestMapping(value = "/editAddress", method = RequestMethod.POST)
    public CommonResult edit(@ApiParam(value = "版本号", allowableValues = "v1", required = true)
                             @PathVariable(value = "version") String version,
                             @Valid @RequestBody UmsUserReceiverAddressParam umsUserReceiverAddressParam,
                             BindingResult bindingResult) {
        boolean isSuccess;
        UmsUserReceiverAddress umsUserReceiverAddress = new UmsUserReceiverAddress();
        try {
            umsUserReceiverAddress.setPhoneNumber(umsUserReceiverAddressParam.getReceiverPhone());
            umsUserReceiverAddress.setName(umsUserReceiverAddressParam.getReceiverName());
            umsUserReceiverAddress.setRegion(umsUserReceiverAddressParam.getReceiverRegion());
            umsUserReceiverAddress.setDetailAddress(umsUserReceiverAddressParam.getReceiverAddr());
            umsUserReceiverAddress.setDefaultStatus(umsUserReceiverAddressParam.getIsDefault());
            isSuccess = receiverAddressService.update(umsUserReceiverAddressParam.getAddressId(), umsUserReceiverAddress) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }

        if (!isSuccess) {
            return CommonResult.failed("编辑地址失败");
        }

        return CommonResult.success(null, "编辑地址成功");
    }


    @ApiOperation(value = "根据区域码获取对应的区域下的列表")
    @ApiVersion(1)
    @RequestMapping(value = "/getAreaList", method = RequestMethod.POST)
    public CommonResult<List<CommonAddressResult>> getArea(@ApiParam(value = "版本号", allowableValues = "v1", required = true)
                             @PathVariable(value = "version") String version,
                             @Valid @RequestBody AreaParams areaParams,
                             BindingResult bindingResult) {

        List<CommonAddressResult> list = null;
        try {
            list = receiverAddressService.getAreaAddress(areaParams);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("获取区域列表信息失败");
        }

        return CommonResult.success(list, "获取区域列表信息成功");
    }
}
