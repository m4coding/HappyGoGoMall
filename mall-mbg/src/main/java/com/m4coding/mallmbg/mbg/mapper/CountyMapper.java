package com.m4coding.mallmbg.mbg.mapper;

import com.m4coding.mallmbg.mbg.model.County;
import com.m4coding.mallmbg.mbg.model.CountyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CountyMapper {
    long countByExample(CountyExample example);

    int deleteByExample(CountyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(County record);

    int insertSelective(County record);

    List<County> selectByExample(CountyExample example);

    County selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") County record, @Param("example") CountyExample example);

    int updateByExample(@Param("record") County record, @Param("example") CountyExample example);

    int updateByPrimaryKeySelective(County record);

    int updateByPrimaryKey(County record);
}