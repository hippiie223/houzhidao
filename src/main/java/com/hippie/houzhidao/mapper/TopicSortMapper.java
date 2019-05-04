package com.hippie.houzhidao.mapper;

import com.hippie.houzhidao.domain.TopicSort;
import com.hippie.houzhidao.domain.example.TopicSortExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface TopicSortMapper {
    int countByExample(TopicSortExample example);

    int deleteByExample(TopicSortExample example);

    int insert(TopicSort record);

    int insertSelective(TopicSort record);

    List<TopicSort> selectByExampleWithRowbounds(TopicSortExample example, RowBounds rowBounds);

    List<TopicSort> selectByExample(TopicSortExample example);

    int updateByExampleSelective(@Param("record") TopicSort record, @Param("example") TopicSortExample example);

    int updateByExample(@Param("record") TopicSort record, @Param("example") TopicSortExample example);
}