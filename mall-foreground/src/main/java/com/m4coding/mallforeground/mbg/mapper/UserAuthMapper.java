package com.m4coding.mallforeground.mbg.mapper;

import com.m4coding.mallforeground.mbg.model.UserAuth;
import com.m4coding.mallforeground.mbg.model.UserAuthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAuthMapper {
    long countByExample(UserAuthExample example);

    int deleteByExample(UserAuthExample example);

    int deleteByPrimaryKey(Integer authId);

    int insert(UserAuth record);

    int insertSelective(UserAuth record);

    List<UserAuth> selectByExample(UserAuthExample example);

    UserAuth selectByPrimaryKey(Integer authId);

    int updateByExampleSelective(@Param("record") UserAuth record, @Param("example") UserAuthExample example);

    int updateByExample(@Param("record") UserAuth record, @Param("example") UserAuthExample example);

    int updateByPrimaryKeySelective(UserAuth record);

    int updateByPrimaryKey(UserAuth record);
}