package com.m4coding.mallforeground.service.impl;

import com.m4coding.mallforeground.service.UmsUserReceiverAddressService;
import com.m4coding.mallforeground.service.UmsUserService;
import com.m4coding.mallmbg.mbg.mapper.UmsUserReceiverAddressMapper;
import com.m4coding.mallmbg.mbg.model.UmsUser;
import com.m4coding.mallmbg.mbg.model.UmsUserReceiverAddress;
import com.m4coding.mallmbg.mbg.model.UmsUserReceiverAddressExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 收货人地址管理服务具体实现
 */
@Service
public class UmsUserReceiverAddressServiceImpl implements UmsUserReceiverAddressService {

    @Autowired
    private UmsUserService umsUserService;
    @Autowired
    private UmsUserReceiverAddressMapper umsUserReceiverAddressMapper;

    @Override
    public int add(UmsUserReceiverAddress address) {
        UmsUser umsUser = umsUserService.getCurrentUser();
        address.setUserId(umsUser.getUserId().longValue());
        return umsUserReceiverAddressMapper.insert(address);
    }

    @Override
    public int delete(Long id) {
        UmsUser umsUser = umsUserService.getCurrentUser();
        UmsUserReceiverAddressExample example = new UmsUserReceiverAddressExample();
        example.createCriteria().andUserIdEqualTo(umsUser.getUserId().longValue()).andIdEqualTo(id);
        return umsUserReceiverAddressMapper.deleteByExample(example);
    }

    @Override
    public int update(Long id, UmsUserReceiverAddress address) {
        address.setId(null);
        UmsUser umsUser = umsUserService.getCurrentUser();
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
}
