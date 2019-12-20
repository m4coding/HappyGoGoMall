package com.m4coding.mallmanager.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.m4coding.mallbase.api.CommonPage;
import com.m4coding.mallmanager.dto.OmsOrderReturnApplyDetailResult;
import com.m4coding.mallmanager.dto.OmsReturnApplyQueryParam;
import com.m4coding.mallmanager.dto.OmsReturnApplyUpdateParam;
import com.m4coding.mallmanager.service.OmsOrderReturnApplyService;
import com.m4coding.mallmbg.mbg.mapper.OmsCompanyAddressMapper;
import com.m4coding.mallmbg.mbg.mapper.OmsOrderReturnApplyMapper;
import com.m4coding.mallmbg.mbg.model.OmsCompanyAddress;
import com.m4coding.mallmbg.mbg.model.OmsOrderReturnApply;
import com.m4coding.mallmbg.mbg.model.OmsOrderReturnApplyExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 售后申请管理服务具体实现类
 */
@Service
public class OmsOrderReturnApplyServiceImpl implements OmsOrderReturnApplyService {

    @Autowired
    private OmsOrderReturnApplyMapper omsOrderReturnApplyMapper;
    @Autowired
    private OmsCompanyAddressMapper omsCompanyAddressMapper;

    @Override
    public CommonPage<OmsOrderReturnApply> getList(OmsReturnApplyQueryParam queryParam, Integer pageSize, Integer pageNum) {
        Page page = PageHelper.startPage(pageNum, pageSize);
        OmsOrderReturnApplyExample omsOrderReturnApplyExample = new OmsOrderReturnApplyExample();
        OmsOrderReturnApplyExample.Criteria criteria = omsOrderReturnApplyExample.createCriteria()
                .andOrderIdEqualTo(queryParam.getId())
                .andStatusEqualTo(queryParam.getStatus())
                .andHandleManEqualTo(queryParam.getHandleMan())
                .andHandleTimeEqualTo(queryParam.getHandleTime().intValue())
                .andCreateTimeEqualTo(queryParam.getCreateTime().intValue());

        //关键字搜索
        if (!StrUtil.isEmpty(queryParam.getReceiverKeyword())) {
            String temp = "%" + queryParam.getReceiverKeyword() + "%";
            criteria.andReturnNameLike(temp)
                    .andReturnPhoneLike(temp);
        }

        return CommonPage.restPage(page, omsOrderReturnApplyMapper.selectByExample(omsOrderReturnApplyExample));
    }

    @Override
    public int delete(List<Long> ids) {
        OmsOrderReturnApplyExample example = new OmsOrderReturnApplyExample();
        example.createCriteria().andIdIn(ids).andStatusEqualTo(3);
        return omsOrderReturnApplyMapper.deleteByExample(example);
    }

    @Override
    public int updateStatus(Long id, OmsReturnApplyUpdateParam updateParam) {
        Integer status = updateParam.getStatus();
        OmsOrderReturnApply returnApply = new OmsOrderReturnApply();
        if(status.equals(1)){
            //确认退货
            returnApply.setId(id);
            returnApply.setStatus(1);
            returnApply.setReturnAmount(updateParam.getReturnAmount());
            returnApply.setCompanyAddressId(updateParam.getCompanyAddressId());
            returnApply.setHandleTime((int) DateUtil.currentSeconds());
            returnApply.setHandleMan(updateParam.getHandleMan());
            returnApply.setHandleNote(updateParam.getHandleNote());
        }else if(status.equals(2)){
            //完成退货
            returnApply.setId(id);
            returnApply.setStatus(2);
            returnApply.setReceiveTime((int) DateUtil.currentSeconds());
            returnApply.setReceiveMan(updateParam.getReceiveMan());
            returnApply.setReceiveNote(updateParam.getReceiveNote());
        }else if(status.equals(3)){
            //拒绝退货
            returnApply.setId(id);
            returnApply.setStatus(3);
            returnApply.setHandleTime((int) DateUtil.currentSeconds());
            returnApply.setHandleMan(updateParam.getHandleMan());
            returnApply.setHandleNote(updateParam.getHandleNote());
        }else{
            return 0;
        }
        return omsOrderReturnApplyMapper.updateByPrimaryKeySelective(returnApply);
    }

    @Override
    public OmsOrderReturnApplyDetailResult getDetailItem(Long id) {
        OmsOrderReturnApplyDetailResult returnApplyDetailResult = null;
        OmsOrderReturnApply omsOrderReturnApply = omsOrderReturnApplyMapper.selectByPrimaryKey(id);
        if (omsOrderReturnApply != null) {
            returnApplyDetailResult = new OmsOrderReturnApplyDetailResult();
            OmsCompanyAddress omsCompanyAddress = omsCompanyAddressMapper.selectByPrimaryKey(omsOrderReturnApply.getCompanyAddressId());
            returnApplyDetailResult.setCompanyAddress(omsCompanyAddress);
        }

        return returnApplyDetailResult;
    }
}
