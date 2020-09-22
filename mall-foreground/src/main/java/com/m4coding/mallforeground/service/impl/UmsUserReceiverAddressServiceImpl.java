package com.m4coding.mallforeground.service.impl;

import cn.hutool.core.util.StrUtil;
import com.m4coding.mallforeground.dto.AreaParams;
import com.m4coding.mallforeground.dto.CommonAddressResult;
import com.m4coding.mallforeground.service.UmsUserReceiverAddressService;
import com.m4coding.mallforeground.service.UmsUserService;
import com.m4coding.mallmbg.mbg.mapper.*;
import com.m4coding.mallmbg.mbg.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 收货人地址管理服务具体实现
 */
@Service
public class UmsUserReceiverAddressServiceImpl implements UmsUserReceiverAddressService {

    @Autowired
    private UmsUserService umsUserService;
    @Autowired
    private UmsUserReceiverAddressMapper umsUserReceiverAddressMapper;
    @Autowired
    private ProvinceMapper provinceMapper;
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private CountyMapper countyMapper;
    @Autowired
    private TownMapper townMapper;
    @Autowired
    private VillageMapper villageMapper;

    @Transactional
    @Override
    public int add(UmsUserReceiverAddress address) {
        UmsUser umsUser = umsUserService.getCurrentUser();
        address.setUserId(umsUser.getUserId().longValue());

        if (address.getDefaultStatus() == 1) { //如果设置为默认地址，其他地址要置为不是默认地址
            UmsUserReceiverAddressExample tempExample = new UmsUserReceiverAddressExample();
            tempExample.createCriteria().andUserIdEqualTo(umsUser.getUserId().longValue());
            UmsUserReceiverAddress tempAddress = new UmsUserReceiverAddress();
            tempAddress.setId(null);
            tempAddress.setDefaultStatus(0);
            umsUserReceiverAddressMapper.updateByExampleSelective(tempAddress,tempExample);
        }

        return umsUserReceiverAddressMapper.insert(address);
    }

    @Transactional
    @Override
    public int delete(Long id) {
        UmsUser umsUser = umsUserService.getCurrentUser();
        UmsUserReceiverAddressExample example = new UmsUserReceiverAddressExample();
        example.createCriteria().andUserIdEqualTo(umsUser.getUserId().longValue()).andIdEqualTo(id);
        return umsUserReceiverAddressMapper.deleteByExample(example);
    }

    @Transactional
    @Override
    public int update(Long id, UmsUserReceiverAddress address) {
        address.setId(null);

        UmsUser umsUser = umsUserService.getCurrentUser();

        if (address.getDefaultStatus() == 1) { //如果设置为默认地址，其他地址要置为不是默认地址
            UmsUserReceiverAddressExample tempExample = new UmsUserReceiverAddressExample();
            tempExample.createCriteria().andUserIdEqualTo(umsUser.getUserId().longValue()).andIdNotEqualTo(id);
            UmsUserReceiverAddress tempAddress = new UmsUserReceiverAddress();
            tempAddress.setId(null);
            tempAddress.setDefaultStatus(0);
            umsUserReceiverAddressMapper.updateByExampleSelective(tempAddress,tempExample);
        }

        UmsUserReceiverAddressExample example = new UmsUserReceiverAddressExample();
        example.createCriteria().andUserIdEqualTo(umsUser.getUserId().longValue()).andIdEqualTo(id);
        return umsUserReceiverAddressMapper.updateByExampleSelective(address,example);
    }

    @Override
    public List<UmsUserReceiverAddress> list() {
        UmsUser umsUser = umsUserService.getCurrentUser();
        UmsUserReceiverAddressExample example = new UmsUserReceiverAddressExample();
        example.createCriteria().andUserIdEqualTo(umsUser.getUserId().longValue());
        return umsUserReceiverAddressMapper.selectByExample(example);
    }

    @Override
    public UmsUserReceiverAddress getItem(Long id) {
        UmsUser umsUser = umsUserService.getCurrentUser();
        UmsUserReceiverAddressExample example = new UmsUserReceiverAddressExample();
        example.createCriteria().andUserIdEqualTo(umsUser.getUserId().longValue()).andIdEqualTo(id);
        List<UmsUserReceiverAddress> addressList = umsUserReceiverAddressMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(addressList)){
            return addressList.get(0);
        }
        return null;
    }

    @Override
    public List<CommonAddressResult> getAreaAddress(AreaParams areaParams) throws Exception {

        String areaCode = areaParams.getAreaCode();
        List<CommonAddressResult> addressResulList = null;

        if (StrUtil.isEmpty(areaCode)) { //当为空时，默认获取的是省列表
            ProvinceExample provinceExample = new ProvinceExample();
            List<Province> provinceList = provinceMapper.selectByExample(provinceExample);
            addressResulList = provinceList.stream().map(new Function<Province, CommonAddressResult>() {
                @Override
                public CommonAddressResult apply(Province province) {
                    CommonAddressResult commonAddressResult = new CommonAddressResult();
                    commonAddressResult.setId(province.getProvinceId());
                    commonAddressResult.setName(province.getName());
                    commonAddressResult.setType(CommonAddressResult.TYPE_PROVINCE);
                    return commonAddressResult;
                }
            }).collect(Collectors.toList());
        } else {
            if (12 == areaCode.length()) { //地址码限定为12位
                int allTotalBits = areaCode.length();
                for (int i = areaCode.length() - 1; i >= 0; i--) {
                    int code = Integer.parseInt(areaCode.substring(i, i + 1));
                    if (code == 0) {
                        allTotalBits--;
                    } else {
                        break; //不是0，表示已到当前截止为，跳出循环
                    }
                }

                /**
                 * 区域码划分规则
                 * 第一、二位表示省（自治区、直辖市、特别行政区）。
                 * 第三、四位表示市（地区、自治州、盟及国家直辖市所属市辖区和县的汇总码）。其中，01-20，51-70表示省直辖市；21-50表示地区（自治州、盟）。
                 * 第五、六位表示县（市辖区、县级市、旗）。01-18表示市辖区或地区（自治州、盟）辖县级市；21-80表示县（旗）；81-99表示省直辖县级市。
                 * 第七至九位表示乡、镇（街道办事处）
                 */
                switch (allTotalBits) {
                    case 1:
                    case 2:
                        CityExample cityExample  = new CityExample();
                        cityExample.createCriteria().andProvinceIdEqualTo(areaCode);
                        List<City> cityList = cityMapper.selectByExample(cityExample);
                        addressResulList = cityList.stream().map(new Function<City, CommonAddressResult>() {
                            @Override
                            public CommonAddressResult apply(City city) {
                                CommonAddressResult commonAddressResult = new CommonAddressResult();
                                commonAddressResult.setId(city.getCityId());
                                commonAddressResult.setName(city.getName());
                                commonAddressResult.setType(CommonAddressResult.TYPE_CITY);
                                return commonAddressResult;
                            }
                        }).collect(Collectors.toList());
                        break;
                    case 3:
                    case 4:
                        CountyExample countyExample = new CountyExample();
                        countyExample.createCriteria().andCityIdEqualTo(areaCode);
                        List<County> countyList = countyMapper.selectByExample(countyExample);
                        addressResulList = countyList.stream().map(new Function<County, CommonAddressResult>() {
                            @Override
                            public CommonAddressResult apply(County county) {
                                CommonAddressResult commonAddressResult = new CommonAddressResult();
                                commonAddressResult.setId(county.getCountyId());
                                commonAddressResult.setName(county.getName());
                                commonAddressResult.setType(CommonAddressResult.TYPE_REGION);
                                return commonAddressResult;
                            }
                        }).collect(Collectors.toList());
                        break;
                    case 5:
                    case 6:
                        TownExample townExample = new TownExample();
                        townExample.createCriteria().andCountyIdEqualTo(areaCode);
                        List<Town> townList = townMapper.selectByExample(townExample);
                        addressResulList = townList.stream().map(new Function<Town, CommonAddressResult>() {
                            @Override
                            public CommonAddressResult apply(Town town) {
                                CommonAddressResult commonAddressResult = new CommonAddressResult();
                                commonAddressResult.setId(town.getTownId());
                                commonAddressResult.setName(town.getName());
                                commonAddressResult.setType(CommonAddressResult.TYPE_STREET);
                                return commonAddressResult;
                            }
                        }).collect(Collectors.toList());
                        break;
                    case 7:
                    case 8:
                    case 9:
                        break;
                }
            } else {
                throw new Exception("地址区域码，不符合规范");
            }
        }

        return addressResulList;
    }
}
