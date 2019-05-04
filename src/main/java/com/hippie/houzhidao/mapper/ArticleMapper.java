package com.hippie.houzhidao.mapper;

import com.hippie.houzhidao.domain.Article;
import com.hippie.houzhidao.domain.ArticleDTO;
import com.hippie.houzhidao.domain.example.ArticleExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface ArticleMapper {
    int countByExample(ArticleExample example);

    int deleteByExample(ArticleExample example);

    int insert(Article record);

    int insertSelective(Article record);

    List<Article> selectByExampleWithBLOBsWithRowbounds(ArticleExample example, RowBounds rowBounds);

    List<Article> selectByExampleWithBLOBs(ArticleExample example);

    List<Article> selectByExampleWithRowbounds(ArticleExample example, RowBounds rowBounds);

    List<Article> selectByExample(ArticleExample example);

    int updateByExampleSelective(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByExampleWithBLOBs(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByExample(@Param("record") Article record, @Param("example") ArticleExample example);

    List<ArticleDTO> getAllList();

    List<ArticleDTO> getTopList();

    List<ArticleDTO> searchArticle(@Param("keyWord") String keyWord);

    List<ArticleDTO> getArticle(@Param("title") String title, @Param("authorName") String authorName);

    List<ArticleDTO> getArticleByAuthor(@Param("authorName") String authorName);

    int updatePostNum(@Param("articleId") Integer articleId);
}