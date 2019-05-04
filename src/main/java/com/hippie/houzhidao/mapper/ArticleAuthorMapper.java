package com.hippie.houzhidao.mapper;

import com.hippie.houzhidao.domain.ArticleAuthor;
import com.hippie.houzhidao.domain.ArticleAuthorDTO;
import com.hippie.houzhidao.domain.example.ArticleAuthorExample;
import com.hippie.houzhidao.domain.ArticleAuthor;
import com.hippie.houzhidao.domain.ArticleAuthorDTO;
import com.hippie.houzhidao.domain.example.ArticleAuthorExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface ArticleAuthorMapper {
    int countByExample(ArticleAuthorExample example);

    int deleteByExample(ArticleAuthorExample example);

    int insert(ArticleAuthor record);

    int insertSelective(ArticleAuthor record);

    List<ArticleAuthor> selectByExampleWithRowbounds(ArticleAuthorExample example, RowBounds rowBounds);

    List<ArticleAuthor> selectByExample(ArticleAuthorExample example);

    int updateByExampleSelective(@Param("record") ArticleAuthor record, @Param("example") ArticleAuthorExample example);

    int updateByExample(@Param("record") ArticleAuthor record, @Param("example") ArticleAuthorExample example);

    List<ArticleAuthorDTO> getTopAuthor();

    int changeArticleNum(@Param("authorName") String authorName);
}