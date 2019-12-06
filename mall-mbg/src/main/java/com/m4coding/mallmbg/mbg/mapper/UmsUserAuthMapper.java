package com.m4coding.mallmbg.mbg.mapper;

import com.m4coding.mallmbg.mbg.model.UmsUserAuth;
import com.m4coding.mallmbg.mbg.model.UmsUserAuthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsUserAuthMapper {
    long countByExample(UmsUserAuthExample example);

    int deleteByExample(UmsUserAuthExample example);

    int deleteByPrimaryKey(Integer authId);

    int insert(UmsUserAuth record);

    int insertSelective(UmsUserAuth record);

    List<UmsUserAuth> selectByExample(UmsUserAuthExample example);

    UmsUserAuth selectByPrimaryKey(Integer authId);

    int updateByExampleSelective(@Param("record") UmsUserAuth record, @Param("example") UmsUserAuthExample example);

    int updateByExample(@Param("record") UmsUserAuth record, @Param("example") UmsUserAuthExample example);

    int updateByPrimaryKeySelective(UmsUserAuth record);

    int updateByPrimaryKey(UmsUserAuth record);
}