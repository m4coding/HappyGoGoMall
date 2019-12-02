package com.m4coding.mallforeground.mbg.mapper;

import com.m4coding.mallforeground.mbg.model.Products;
import com.m4coding.mallforeground.mbg.model.ProductsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductsMapper {
    long countByExample(ProductsExample example);

    int deleteByExample(ProductsExample example);

    int deleteByPrimaryKey(Integer productId);

    int insert(Products record);

    int insertSelective(Products record);

    List<Products> selectByExampleWithBLOBs(ProductsExample example);

    List<Products> selectByExample(ProductsExample example);

    Products selectByPrimaryKey(Integer productId);

    int updateByExampleSelective(@Param("record") Products record, @Param("example") ProductsExample example);

    int updateByExampleWithBLOBs(@Param("record") Products record, @Param("example") ProductsExample example);

    int updateByExample(@Param("record") Products record, @Param("example") ProductsExample example);

    int updateByPrimaryKeySelective(Products record);

    int updateByPrimaryKeyWithBLOBs(Products record);

    int updateByPrimaryKey(Products record);
}