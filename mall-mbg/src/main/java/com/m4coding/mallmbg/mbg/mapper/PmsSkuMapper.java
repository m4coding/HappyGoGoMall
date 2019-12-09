package com.m4coding.mallmbg.mbg.mapper;

import com.m4coding.mallmbg.mbg.model.PmsSku;
import com.m4coding.mallmbg.mbg.model.PmsSkuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsSkuMapper {
    long countByExample(PmsSkuExample example);

    int deleteByExample(PmsSkuExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PmsSku record);

    int insertSelective(PmsSku record);

    List<PmsSku> selectByExampleWithBLOBs(PmsSkuExample example);

    List<PmsSku> selectByExample(PmsSkuExample example);

    PmsSku selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PmsSku record, @Param("example") PmsSkuExample example);

    int updateByExampleWithBLOBs(@Param("record") PmsSku record, @Param("example") PmsSkuExample example);

    int updateByExample(@Param("record") PmsSku record, @Param("example") PmsSkuExample example);

    int updateByPrimaryKeySelective(PmsSku record);

    int updateByPrimaryKeyWithBLOBs(PmsSku record);

    int updateByPrimaryKey(PmsSku record);
}