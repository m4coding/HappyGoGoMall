package com.m4coding.mallmbg.mbg.mapper;

import com.m4coding.mallmbg.mbg.model.SmsHomeTab;
import com.m4coding.mallmbg.mbg.model.SmsHomeTabExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsHomeTabMapper {
    long countByExample(SmsHomeTabExample example);

    int deleteByExample(SmsHomeTabExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SmsHomeTab record);

    int insertSelective(SmsHomeTab record);

    List<SmsHomeTab> selectByExample(SmsHomeTabExample example);

    SmsHomeTab selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SmsHomeTab record, @Param("example") SmsHomeTabExample example);

    int updateByExample(@Param("record") SmsHomeTab record, @Param("example") SmsHomeTabExample example);

    int updateByPrimaryKeySelective(SmsHomeTab record);

    int updateByPrimaryKey(SmsHomeTab record);
}