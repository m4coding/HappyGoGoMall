package com.m4coding.mallmbg.mbg.mapper;

import com.m4coding.mallmbg.mbg.model.PmsSpu;
import com.m4coding.mallmbg.mbg.model.PmsSpuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsSpuMapper {
    long countByExample(PmsSpuExample example);

    int deleteByExample(PmsSpuExample example);

    int deleteByPrimaryKey(Long productId);

    int insert(PmsSpu record);

    int insertSelective(PmsSpu record);

    List<PmsSpu> selectByExampleWithBLOBs(PmsSpuExample example);

    List<PmsSpu> selectByExample(PmsSpuExample example);

    PmsSpu selectByPrimaryKey(Long productId);

    int updateByExampleSelective(@Param("record") PmsSpu record, @Param("example") PmsSpuExample example);

    int updateByExampleWithBLOBs(@Param("record") PmsSpu record, @Param("example") PmsSpuExample example);

    int updateByExample(@Param("record") PmsSpu record, @Param("example") PmsSpuExample example);

    int updateByPrimaryKeySelective(PmsSpu record);

    int updateByPrimaryKeyWithBLOBs(PmsSpu record);

    int updateByPrimaryKey(PmsSpu record);
}