package com.hippie.houzhidao.mapper;

import com.hippie.houzhidao.domain.QaAnswer;
import com.hippie.houzhidao.domain.example.QaAnswerExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QaAnswerMapper {
    int countByExample(QaAnswerExample example);

    int deleteByExample(QaAnswerExample example);

    int insert(QaAnswer record);

    int insertSelective(QaAnswer record);

    List<QaAnswer> selectByExampleWithBLOBsWithRowbounds(QaAnswerExample example, RowBounds rowBounds);

    List<QaAnswer> selectByExampleWithBLOBs(QaAnswerExample example);

    List<QaAnswer> selectByExampleWithRowbounds(QaAnswerExample example, RowBounds rowBounds);

    List<QaAnswer> selectByExample(QaAnswerExample example);

    int updateByExampleSelective(@Param("record") QaAnswer record, @Param("example") QaAnswerExample example);

    int updateByExampleWithBLOBs(@Param("record") QaAnswer record, @Param("example") QaAnswerExample example);

    int updateByExample(@Param("record") QaAnswer record, @Param("example") QaAnswerExample example);
}