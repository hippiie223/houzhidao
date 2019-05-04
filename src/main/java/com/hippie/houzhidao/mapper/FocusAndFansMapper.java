package com.hippie.houzhidao.mapper;

import com.hippie.houzhidao.domain.FocusAndFans;
import com.hippie.houzhidao.domain.example.FocusAndFansExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface FocusAndFansMapper {
    int countByExample(FocusAndFansExample example);

    int deleteByExample(FocusAndFansExample example);

    int insert(FocusAndFans record);

    int insertSelective(FocusAndFans record);

    List<FocusAndFans> selectByExampleWithRowbounds(FocusAndFansExample example, RowBounds rowBounds);

    List<FocusAndFans> selectByExample(FocusAndFansExample example);

    int updateByExampleSelective(@Param("record") FocusAndFans record, @Param("example") FocusAndFansExample example);

    int updateByExample(@Param("record") FocusAndFans record, @Param("example") FocusAndFansExample example);

    List<String> getFocusList(@Param("userName") String userName);

    List<String> getFansList(@Param("userName") String userName);
}