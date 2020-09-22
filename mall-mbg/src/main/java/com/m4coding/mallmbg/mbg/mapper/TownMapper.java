package com.m4coding.mallmbg.mbg.mapper;

import com.m4coding.mallmbg.mbg.model.Town;
import com.m4coding.mallmbg.mbg.model.TownExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TownMapper {
    long countByExample(TownExample example);

    int deleteByExample(TownExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Town record);

    int insertSelective(Town record);

    List<Town> selectByExample(TownExample example);

    Town selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Town record, @Param("example") TownExample example);

    int updateByExample(@Param("record") Town record, @Param("example") TownExample example);

    int updateByPrimaryKeySelective(Town record);

    int updateByPrimaryKey(Town record);
}