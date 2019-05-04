package com.hippie.houzhidao.mapper;

import com.hippie.houzhidao.domain.TopicPostReply;
import com.hippie.houzhidao.domain.example.TopicPostReplyExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface TopicPostReplyMapper {
    int countByExample(TopicPostReplyExample example);

    int deleteByExample(TopicPostReplyExample example);

    int insert(TopicPostReply record);

    int insertSelective(TopicPostReply record);

    List<TopicPostReply> selectByExampleWithBLOBsWithRowbounds(TopicPostReplyExample example, RowBounds rowBounds);

    List<TopicPostReply> selectByExampleWithBLOBs(TopicPostReplyExample example);

    List<TopicPostReply> selectByExampleWithRowbounds(TopicPostReplyExample example, RowBounds rowBounds);

    List<TopicPostReply> selectByExample(TopicPostReplyExample example);

    int updateByExampleSelective(@Param("record") TopicPostReply record, @Param("example") TopicPostReplyExample example);

    int updateByExampleWithBLOBs(@Param("record") TopicPostReply record, @Param("example") TopicPostReplyExample example);

    int updateByExample(@Param("record") TopicPostReply record, @Param("example") TopicPostReplyExample example);
}