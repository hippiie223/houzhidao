package com.hippie.houzhidao.mapper;

import com.hippie.houzhidao.domain.QaAnswerReply;
import com.hippie.houzhidao.domain.example.QaAnswerReplyExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QaAnswerReplyMapper {
    int countByExample(QaAnswerReplyExample example);

    int deleteByExample(QaAnswerReplyExample example);

    int insert(QaAnswerReply record);

    int insertSelective(QaAnswerReply record);

    List<QaAnswerReply> selectByExampleWithBLOBsWithRowbounds(QaAnswerReplyExample example, RowBounds rowBounds);

    List<QaAnswerReply> selectByExampleWithBLOBs(QaAnswerReplyExample example);

    List<QaAnswerReply> selectByExampleWithRowbounds(QaAnswerReplyExample example, RowBounds rowBounds);

    List<QaAnswerReply> selectByExample(QaAnswerReplyExample example);

    int updateByExampleSelective(@Param("record") QaAnswerReply record, @Param("example") QaAnswerReplyExample example);

    int updateByExampleWithBLOBs(@Param("record") QaAnswerReply record, @Param("example") QaAnswerReplyExample example);

    int updateByExample(@Param("record") QaAnswerReply record, @Param("example") QaAnswerReplyExample example);
}