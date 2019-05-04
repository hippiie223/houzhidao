package com.hippie.houzhidao.mapper;

import com.hippie.houzhidao.domain.UserInfo;
import com.hippie.houzhidao.domain.example.UserInfoExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface UserInfoMapper {
    int countByExample(UserInfoExample example);

    int deleteByExample(UserInfoExample example);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    List<UserInfo> selectByExampleWithRowbounds(UserInfoExample example, RowBounds rowBounds);

    List<UserInfo> selectByExample(UserInfoExample example);

    int updateByExampleSelective(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    int updateByExample(@Param("record") UserInfo record, @Param("example") UserInfoExample example);
}