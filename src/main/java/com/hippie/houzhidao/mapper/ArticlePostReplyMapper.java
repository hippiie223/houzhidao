package com.hippie.houzhidao.mapper;

import com.hippie.houzhidao.domain.ArticlePostReply;
import com.hippie.houzhidao.domain.example.ArticlePostReplyExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface ArticlePostReplyMapper {
    int countByExample(ArticlePostReplyExample example);

    int deleteByExample(ArticlePostReplyExample example);

    int insert(ArticlePostReply record);

    int insertSelective(ArticlePostReply record);

    List<ArticlePostReply> selectByExampleWithBLOBsWithRowbounds(ArticlePostReplyExample example, RowBounds rowBounds);

    List<ArticlePostReply> selectByExampleWithBLOBs(ArticlePostReplyExample example);

    List<ArticlePostReply> selectByExampleWithRowbounds(ArticlePostReplyExample example, RowBounds rowBounds);

    List<ArticlePostReply> selectByExample(ArticlePostReplyExample example);

    int updateByExampleSelective(@Param("record") ArticlePostReply record, @Param("example") ArticlePostReplyExample example);

    int updateByExampleWithBLOBs(@Param("record") ArticlePostReply record, @Param("example") ArticlePostReplyExample example);

    int updateByExample(@Param("record") ArticlePostReply record, @Param("example") ArticlePostReplyExample example);
}