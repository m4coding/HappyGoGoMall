package com.m4coding.mallmbg.mbg.mapper;

import com.m4coding.mallmbg.mbg.model.PmsSpuSkuAttr;
import com.m4coding.mallmbg.mbg.model.PmsSpuSkuAttrExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsSpuSkuAttrMapper {
    long countByExample(PmsSpuSkuAttrExample example);

    int deleteByExample(PmsSpuSkuAttrExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PmsSpuSkuAttr record);

    int insertSelective(PmsSpuSkuAttr record);

    List<PmsSpuSkuAttr> selectByExample(PmsSpuSkuAttrExample example);

    PmsSpuSkuAttr selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PmsSpuSkuAttr record, @Param("example") PmsSpuSkuAttrExample example);

    int updateByExample(@Param("record") PmsSpuSkuAttr record, @Param("example") PmsSpuSkuAttrExample example);

    int updateByPrimaryKeySelective(PmsSpuSkuAttr record);

    int updateByPrimaryKey(PmsSpuSkuAttr record);
}