package com.hippie.houzhidao.mapper;

import com.hippie.houzhidao.domain.Article;
import com.hippie.houzhidao.domain.example.ArticleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

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
}