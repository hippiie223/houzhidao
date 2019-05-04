package com.hippie.houzhidao.service;

import com.hippie.houzhidao.domain.*;
import com.hippie.houzhidao.respbody.ArticleRespBody;


import java.util.List;

/**
 * @author 39239
 * @Date 2019/4/30 13:55
 * @Package com.hippie.houzhidao.service
 * @Description:
 */

public interface ArticleService {
    List<ArticleRespBody> getAllArticleList(int pageNum, int pageSize);
    List<ArticleRespBody> getTopList();
    List<ArticleAuthorDTO> getTopAuthorList();
    List<ArticleRespBody> getSearchList(String keyWord, int pageNum, int pageSize);
    List<ArticleRespBody> getArticleDetail(String title, String authorName);
    List<ArticleRespBody> getArticleByAuthor(String authorName);
    void articlePost(ArticlePost articlePost);
    void articlePostReply(ArticlePostReply articlePostReply);
    void insertArticle(Article article);
    Integer getSortIdByName(String sortName);

}
