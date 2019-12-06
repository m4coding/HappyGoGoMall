package com.m4coding.mallmbg.mbg.mapper;

import com.m4coding.mallmbg.mbg.model.UmsAdminAuth;
import com.m4coding.mallmbg.mbg.model.UmsAdminAuthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsAdminAuthMapper {
    long countByExample(UmsAdminAuthExample example);

    int deleteByExample(UmsAdminAuthExample example);

    int deleteByPrimaryKey(Integer authId);

    int insert(UmsAdminAuth record);

    int insertSelective(UmsAdminAuth record);

    List<UmsAdminAuth> selectByExample(UmsAdminAuthExample example);

    UmsAdminAuth selectByPrimaryKey(Integer authId);

    int updateByExampleSelective(@Param("record") UmsAdminAuth record, @Param("example") UmsAdminAuthExample example);

    int updateByExample(@Param("record") UmsAdminAuth record, @Param("example") UmsAdminAuthExample example);

    int updateByPrimaryKeySelective(UmsAdminAuth record);

    int updateByPrimaryKey(UmsAdminAuth record);
}