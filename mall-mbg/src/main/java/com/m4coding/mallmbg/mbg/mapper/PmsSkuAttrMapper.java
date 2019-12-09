package com.m4coding.mallmbg.mbg.mapper;

import com.m4coding.mallmbg.mbg.model.PmsSkuAttr;
import com.m4coding.mallmbg.mbg.model.PmsSkuAttrExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsSkuAttrMapper {
    long countByExample(PmsSkuAttrExample example);

    int deleteByExample(PmsSkuAttrExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PmsSkuAttr record);

    int insertSelective(PmsSkuAttr record);

    List<PmsSkuAttr> selectByExample(PmsSkuAttrExample example);

    PmsSkuAttr selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PmsSkuAttr record, @Param("example") PmsSkuAttrExample example);

    int updateByExample(@Param("record") PmsSkuAttr record, @Param("example") PmsSkuAttrExample example);

    int updateByPrimaryKeySelective(PmsSkuAttr record);

    int updateByPrimaryKey(PmsSkuAttr record);
}