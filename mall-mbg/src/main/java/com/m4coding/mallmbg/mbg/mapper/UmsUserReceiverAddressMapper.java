package com.m4coding.mallmbg.mbg.mapper;

import com.m4coding.mallmbg.mbg.model.UmsUserReceiverAddress;
import com.m4coding.mallmbg.mbg.model.UmsUserReceiverAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsUserReceiverAddressMapper {
    long countByExample(UmsUserReceiverAddressExample example);

    int deleteByExample(UmsUserReceiverAddressExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsUserReceiverAddress record);

    int insertSelective(UmsUserReceiverAddress record);

    List<UmsUserReceiverAddress> selectByExample(UmsUserReceiverAddressExample example);

    UmsUserReceiverAddress selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsUserReceiverAddress record, @Param("example") UmsUserReceiverAddressExample example);

    int updateByExample(@Param("record") UmsUserReceiverAddress record, @Param("example") UmsUserReceiverAddressExample example);

    int updateByPrimaryKeySelective(UmsUserReceiverAddress record);

    int updateByPrimaryKey(UmsUserReceiverAddress record);
}