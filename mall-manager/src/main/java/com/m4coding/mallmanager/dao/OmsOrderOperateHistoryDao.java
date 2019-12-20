package com.m4coding.mallmanager.dao;

import com.m4coding.mallmbg.mbg.model.OmsOrderOperateHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *订单操作记录自定义Dao
 */
public interface OmsOrderOperateHistoryDao {
    /**
     * 插入数据
     * @param list
     * @return
     */
    int insertList(@Param("list")List<OmsOrderOperateHistory> list);
}
