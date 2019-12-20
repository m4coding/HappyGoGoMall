package com.m4coding.mallmanager.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.m4coding.mallbase.api.CommonPage;
import com.m4coding.mallmanager.dao.OmsOrderDao;
import com.m4coding.mallmanager.dao.OmsOrderOperateHistoryDao;
import com.m4coding.mallmanager.dto.OmsOrderDeliveryParam;
import com.m4coding.mallmanager.dto.OmsOrderDetail;
import com.m4coding.mallmanager.dto.OmsOrderQueryParam;
import com.m4coding.mallmanager.dto.OmsReceiverInfoParam;
import com.m4coding.mallmanager.service.OmsOrderService;
import com.m4coding.mallmbg.mbg.mapper.OmsOrderItemMapper;
import com.m4coding.mallmbg.mbg.mapper.OmsOrderMapper;
import com.m4coding.mallmbg.mbg.mapper.OmsOrderOperateHistoryMapper;
import com.m4coding.mallmbg.mbg.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单服务管理具体实现类
 */
@Service
public class OmsOrderServiceImpl implements OmsOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OmsOrderServiceImpl.class);

    @Autowired
    private OmsOrderMapper omsOrderMapper;
    @Autowired
    private OmsOrderItemMapper omsOrderItemMapper;
    @Autowired
    private OmsOrderOperateHistoryMapper omsOrderOperateHistoryMapper;
    @Autowired
    private OmsOrderDao omsOrderDao;
    @Autowired
    private OmsOrderOperateHistoryDao omsOrderOperateHistoryDao;


    @Override
    public CommonPage<OmsOrder> getList(OmsOrderQueryParam omsOrderQueryParam) {
        Page page = PageHelper.startPage(omsOrderQueryParam.getPageNum(), omsOrderQueryParam.getPageSize());
        OmsOrderExample omsOrderExample = new OmsOrderExample();
        OmsOrderExample.Criteria criteria = omsOrderExample.createCriteria().andOrderSnEqualTo(omsOrderQueryParam.getOrderSn())
                .andStatusEqualTo(omsOrderQueryParam.getStatus())
                .andOrderTypeEqualTo(omsOrderQueryParam.getOrderType())
                .andSourceTypeEqualTo(omsOrderQueryParam.getSourceType())
                .andCreateTimeEqualTo(omsOrderQueryParam.getCreateTime());

        //关键字搜索
        if (!StrUtil.isEmpty(omsOrderQueryParam.getReceiverKeyword())) {
            String temp = "%" + omsOrderQueryParam.getReceiverKeyword() + "%";
            criteria.andReceiverNameLike(temp)
                    .andReceiverPhoneLike(temp);
        }

        return CommonPage.restPage(page, omsOrderMapper.selectByExample(omsOrderExample));
    }

    @Override
    public int delivery(List<OmsOrderDeliveryParam> deliveryParamList) {
        //批量发货
        int count = omsOrderDao.delivery(deliveryParamList);
        //添加操作记录
        List<OmsOrderOperateHistory> operateHistoryList = deliveryParamList.stream()
                .map(omsOrderDeliveryParam -> {
                    OmsOrderOperateHistory history = new OmsOrderOperateHistory();
                    history.setOrderId(omsOrderDeliveryParam.getOrderId());
                    history.setCreateTime((int) DateUtil.currentSeconds());
                    history.setOperateMan("后台管理员");
                    history.setOrderStatus(2);
                    history.setNote("完成发货");
                    return history;
                }).collect(Collectors.toList());
        omsOrderOperateHistoryDao.insertList(operateHistoryList);
        return count;
    }

    @Override
    public int close(List<Long> ids, String note) {
        OmsOrder omsOrder = new OmsOrder();
        omsOrder.setStatus(4);
        OmsOrderExample omsOrderExample = new OmsOrderExample();
        omsOrderExample.createCriteria().andIdIn(ids);
        int count = omsOrderMapper.updateByExampleSelective(omsOrder, omsOrderExample);

        List<OmsOrderOperateHistory> historyList = ids.stream().map(orderId -> {
            OmsOrderOperateHistory history = new OmsOrderOperateHistory();
            history.setOrderId(orderId);
            history.setCreateTime((int) DateUtil.currentSeconds());
            history.setOperateMan("后台管理员");
            history.setOrderStatus(4);
            history.setNote("订单关闭:" + note);
            return history;
        }).collect(Collectors.toList());

        omsOrderOperateHistoryDao.insertList(historyList);

        return count;
    }

    @Override
    public int delete(List<Long> ids) {
        OmsOrder omsOrder = new OmsOrder();
        omsOrder.setDeleteStatus(1);
        OmsOrderExample omsOrderExample = new OmsOrderExample();
        omsOrderExample.createCriteria().andIdIn(ids);
        return omsOrderMapper.updateByExampleSelective(omsOrder, omsOrderExample);
    }

    @Override
    public OmsOrderDetail getDetail(Long id) {

        OmsOrderDetail omsOrderDetail = null;

        OmsOrderExample omsOrderExample = new OmsOrderExample();
        omsOrderExample.createCriteria().andIdEqualTo(id);
        List<OmsOrder> listOrder = omsOrderMapper.selectByExample(omsOrderExample);
        if (!CollectionUtil.isEmpty(listOrder)) {
            omsOrderDetail = new OmsOrderDetail();
            BeanUtils.copyProperties(listOrder.get(0), omsOrderDetail);

            OmsOrderItemExample omsOrderItemExample = new OmsOrderItemExample();
            omsOrderItemExample.createCriteria().andOrderIdEqualTo(id);
            List<OmsOrderItem> itemList = omsOrderItemMapper.selectByExample(omsOrderItemExample);
            omsOrderDetail.setOrderItemList(itemList);

            OmsOrderOperateHistoryExample omsOrderOperateHistoryExample = new OmsOrderOperateHistoryExample();
            omsOrderOperateHistoryExample.createCriteria().andOrderIdEqualTo(id);
            List<OmsOrderOperateHistory> historyList= omsOrderOperateHistoryMapper.selectByExample(omsOrderOperateHistoryExample);
            omsOrderDetail.setHistoryList(historyList);
        }

        return omsOrderDetail;
    }

    @Override
    public int updateReceiverInfo(OmsReceiverInfoParam receiverInfoParam) {
        OmsOrder order = new OmsOrder();
        order.setId(receiverInfoParam.getOrderId());
        order.setReceiverName(receiverInfoParam.getReceiverName());
        order.setReceiverPhone(receiverInfoParam.getReceiverPhone());
        order.setReceiverPostCode(receiverInfoParam.getReceiverPostCode());
        order.setReceiverDetailAddress(receiverInfoParam.getReceiverDetailAddress());
        order.setReceiverProvince(receiverInfoParam.getReceiverProvince());
        order.setReceiverCity(receiverInfoParam.getReceiverCity());
        order.setReceiverRegion(receiverInfoParam.getReceiverRegion());
        order.setModifyTime((int) DateUtil.currentSeconds());
        int count = omsOrderMapper.updateByPrimaryKeySelective(order);
        //插入操作记录
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(receiverInfoParam.getOrderId());
        history.setCreateTime((int) DateUtil.currentSeconds());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(receiverInfoParam.getStatus());
        history.setNote("修改收货人信息");
        omsOrderOperateHistoryMapper.insert(history);
        return count;
    }

    @Override
    public int updateNote(Long id, String note, Integer status) {
        OmsOrder order = new OmsOrder();
        order.setId(id);
        order.setNote(note);
        order.setModifyTime((int) DateUtil.currentSeconds());
        int count = omsOrderMapper.updateByPrimaryKeySelective(order);
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(id);
        history.setCreateTime((int) DateUtil.currentSeconds());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(status);
        history.setNote("修改备注信息："+note);
        omsOrderOperateHistoryMapper.insert(history);
        return count;
    }
}
