package com.m4coding.mallmbg.mbg.mapper;

import com.m4coding.mallmbg.mbg.model.UmsUser;
import com.m4coding.mallmbg.mbg.model.UmsUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsUserMapper {
    long countByExample(UmsUserExample example);

    int deleteByExample(UmsUserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(UmsUser record);

    int insertSelective(UmsUser record);

    List<UmsUser> selectByExample(UmsUserExample example);

    UmsUser selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") UmsUser record, @Param("example") UmsUserExample example);

    int updateByExample(@Param("record") UmsUser record, @Param("example") UmsUserExample example);

    int updateByPrimaryKeySelective(UmsUser record);

    int updateByPrimaryKey(UmsUser record);
}