package com.hippie.houzhidao.mapper;

import com.hippie.houzhidao.domain.QaQuestion;
import com.hippie.houzhidao.domain.example.QaQuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface QaQuestionMapper {
    int countByExample(QaQuestionExample example);

    int deleteByExample(QaQuestionExample example);

    int insert(QaQuestion record);

    int insertSelective(QaQuestion record);

    List<QaQuestion> selectByExampleWithBLOBsWithRowbounds(QaQuestionExample example, RowBounds rowBounds);

    List<QaQuestion> selectByExampleWithBLOBs(QaQuestionExample example);

    List<QaQuestion> selectByExampleWithRowbounds(QaQuestionExample example, RowBounds rowBounds);

    List<QaQuestion> selectByExample(QaQuestionExample example);

    int updateByExampleSelective(@Param("record") QaQuestion record, @Param("example") QaQuestionExample example);

    int updateByExampleWithBLOBs(@Param("record") QaQuestion record, @Param("example") QaQuestionExample example);

    int updateByExample(@Param("record") QaQuestion record, @Param("example") QaQuestionExample example);
}