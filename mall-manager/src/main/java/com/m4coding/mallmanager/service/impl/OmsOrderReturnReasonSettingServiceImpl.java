package com.m4coding.mallmanager.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.m4coding.mallbase.api.CommonPage;
import com.m4coding.mallmanager.service.OmsOrderReturnReasonSettingService;
import com.m4coding.mallmbg.mbg.mapper.OmsOrderReturnReasonMapper;
import com.m4coding.mallmbg.mbg.model.OmsOrderReturnReason;
import com.m4coding.mallmbg.mbg.model.OmsOrderReturnReasonExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 售后申请原因设置具体实现类
 */
@Service
public class OmsOrderReturnReasonSettingServiceImpl implements OmsOrderReturnReasonSettingService {

    @Autowired
    private OmsOrderReturnReasonMapper omsOrderReturnReasonMapper;

    @Override
    public int create(OmsOrderReturnReason returnReason) {
        return omsOrderReturnReasonMapper.insertSelective(returnReason);
    }

    @Override
    public int update(Long id, OmsOrderReturnReason returnReason) {
        returnReason.setId(id);
        return omsOrderReturnReasonMapper.updateByPrimaryKeySelective(returnReason);
    }

    @Override
    public int delete(List<Long> ids) {
        OmsOrderReturnReasonExample example = new OmsOrderReturnReasonExample();
        example.createCriteria().andIdIn(ids);
        return omsOrderReturnReasonMapper.deleteByExample(example);
    }

    @Override
    public CommonPage<OmsOrderReturnReason> list(Integer pageSize, Integer pageNum) {
        Page page = PageHelper.startPage(pageNum,pageSize);
        OmsOrderReturnReasonExample example = new OmsOrderReturnReasonExample();
        example.setOrderByClause("sort desc"); //降序排列
        List<OmsOrderReturnReason> list = omsOrderReturnReasonMapper.selectByExample(example);
        return CommonPage.restPage(page, list);
    }

    @Override
    public int updateStatus(List<Long> ids, Integer status) {
        //预防status错误
        if(!status.equals(0)&&!status.equals(1)){
            return 0;
        }

        OmsOrderReturnReason record = new OmsOrderReturnReason();
        record.setStatus(status);
        OmsOrderReturnReasonExample example = new OmsOrderReturnReasonExample();
        example.createCriteria().andIdIn(ids);
        return omsOrderReturnReasonMapper.updateByExampleSelective(record,example);
    }

    @Override
    public OmsOrderReturnReason getItem(Long id) {
        return omsOrderReturnReasonMapper.selectByPrimaryKey(id);
    }
}
