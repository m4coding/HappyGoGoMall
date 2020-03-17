package com.m4coding.mallmbg.mbg.mapper;

import com.m4coding.mallmbg.mbg.model.JdProductItems;
import com.m4coding.mallmbg.mbg.model.JdProductItemsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JdProductItemsMapper {
    long countByExample(JdProductItemsExample example);

    int deleteByExample(JdProductItemsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(JdProductItems record);

    int insertSelective(JdProductItems record);

    List<JdProductItems> selectByExample(JdProductItemsExample example);

    JdProductItems selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") JdProductItems record, @Param("example") JdProductItemsExample example);

    int updateByExample(@Param("record") JdProductItems record, @Param("example") JdProductItemsExample example);

    int updateByPrimaryKeySelective(JdProductItems record);

    int updateByPrimaryKey(JdProductItems record);
}