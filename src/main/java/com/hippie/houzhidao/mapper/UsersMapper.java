package com.hippie.houzhidao.mapper;

import com.hippie.houzhidao.domain.Users;
import com.hippie.houzhidao.domain.example.UsersExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface UsersMapper {
    int countByExample(UsersExample example);

    int deleteByExample(UsersExample example);

    int deleteByPrimaryKey(String phone);

    int insert(Users record);

    int insertSelective(Users record);

    List<Users> selectByExampleWithRowbounds(UsersExample example, RowBounds rowBounds);

    List<Users> selectByExample(UsersExample example);

    Users selectByPrimaryKey(String phone);

    int updateByExampleSelective(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByExample(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
}