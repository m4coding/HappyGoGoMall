package com.m4coding.mallmbg.mbg.mapper;

import com.m4coding.mallmbg.mbg.model.PmsSkuAttrValue;
import com.m4coding.mallmbg.mbg.model.PmsSkuAttrValueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsSkuAttrValueMapper {
    long countByExample(PmsSkuAttrValueExample example);

    int deleteByExample(PmsSkuAttrValueExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PmsSkuAttrValue record);

    int insertSelective(PmsSkuAttrValue record);

    List<PmsSkuAttrValue> selectByExample(PmsSkuAttrValueExample example);

    PmsSkuAttrValue selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PmsSkuAttrValue record, @Param("example") PmsSkuAttrValueExample example);

    int updateByExample(@Param("record") PmsSkuAttrValue record, @Param("example") PmsSkuAttrValueExample example);

    int updateByPrimaryKeySelective(PmsSkuAttrValue record);

    int updateByPrimaryKey(PmsSkuAttrValue record);
}