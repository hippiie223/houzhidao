package com.hippie.houzhidao.service.impl;

import com.github.pagehelper.PageHelper;
import com.hippie.houzhidao.domain.*;
import com.hippie.houzhidao.domain.example.ArticleAuthorExample;
import com.hippie.houzhidao.domain.example.ArticleExample;
import com.hippie.houzhidao.domain.example.ArticlePostExample;
import com.hippie.houzhidao.domain.example.ArticleSortExample;
import com.hippie.houzhidao.mapper.*;
import com.hippie.houzhidao.respbody.ArticleRespBody;
import com.hippie.houzhidao.service.ArticleService;
import com.hippie.houzhidao.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 39239
 * @Date 2019/4/30 13:56
 * @Package com.hippie.houzhidao.service.impl
 * @Description:
 */

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleAuthorMapper articleAuthorMapper;

    @Autowired
    private ArticlePostMapper articlePostMapper;

    @Autowired
    private ArticlePostReplyMapper articlePostReplyMapper;

    @Autowired
    private ArticleSortMapper articleSortMapper;

    @Autowired
    private ExtMapper extMapper;


    @Override
    public List<ArticleRespBody> getAllArticleList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        return extMapper.getAllArticleList().parallelStream().map(articleDTO -> {
            ArticleRespBody articleRespBody = new ArticleRespBody();
            articleRespBody.setId(articleDTO.getId());
            articleRespBody.setTitle(articleDTO.getTitle());
            articleRespBody.setAuthor(articleDTO.getAuthor());
            articleRespBody.setContent(articleDTO.getContent());
            articleRespBody.setPic(articleDTO.getPic());
            articleRespBody.setReviewNum(articleDTO.getReviewNum());
            articleRespBody.setViewNum(articleDTO.getViewNum());
            articleRespBody.setCreateTime(TimeUtil.getTime(articleDTO.getCreateTime()));
            return articleRespBody;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ArticleRespBody> getTopList() {
        return extMapper.getTopArticleList().parallelStream().map(articleDTO -> {
            ArticleRespBody articleRespBody = new ArticleRespBody();
            articleRespBody.setId(articleDTO.getId());
            articleRespBody.setTitle(articleDTO.getTitle());
            articleRespBody.setAuthor(articleDTO.getAuthor());
            articleRespBody.setContent(articleDTO.getContent());
            articleRespBody.setPic(articleDTO.getPic());
            articleRespBody.setReviewNum(articleDTO.getReviewNum());
            articleRespBody.setViewNum(articleDTO.getViewNum());
            articleRespBody.setCreateTime(TimeUtil.getTime(articleDTO.getCreateTime()));
            return articleRespBody;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ArticleAuthorDTO> getTopAuthorList() {
        return articleAuthorMapper.getTopAuthor();
    }

    @Override
    public List<ArticleRespBody> getSearchList(String keyWord, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return extMapper.searchArticle(keyWord).parallelStream().map(articleDTO -> {
            ArticleRespBody articleRespBody = new ArticleRespBody();
            articleRespBody.setId(articleDTO.getId());
            articleRespBody.setTitle(articleDTO.getTitle());
            articleRespBody.setAuthor(articleDTO.getAuthor());
            articleRespBody.setContent(articleDTO.getContent());
            articleRespBody.setPic(articleDTO.getPic());
            articleRespBody.setReviewNum(articleDTO.getReviewNum());
            articleRespBody.setViewNum(articleDTO.getViewNum());
            articleRespBody.setCreateTime(TimeUtil.getTime(articleDTO.getCreateTime()));
            return articleRespBody;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ArticleRespBody> getArticleDetail(String title, String authorName) {
        return extMapper.getArticle(title, authorName).parallelStream().map(articleDTO -> {
            ArticleRespBody articleRespBody = new ArticleRespBody();
            articleRespBody.setId(articleDTO.getId());
            articleRespBody.setTitle(articleDTO.getTitle());
            articleRespBody.setAuthor(articleDTO.getAuthor());
            articleRespBody.setContent(articleDTO.getContent());
            articleRespBody.setPic(articleDTO.getPic());
            articleRespBody.setReviewNum(articleDTO.getReviewNum());
            articleRespBody.setViewNum(articleDTO.getViewNum());
            articleRespBody.setCreateTime(TimeUtil.getTime(articleDTO.getCreateTime()));
            return articleRespBody;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ArticleRespBody> getArticleByAuthor(String authorName) {
        return extMapper.getArticleByAuthor(authorName).parallelStream().map(articleDTO -> {
            ArticleRespBody articleRespBody = new ArticleRespBody();
            articleRespBody.setId(articleDTO.getId());
            articleRespBody.setTitle(articleDTO.getTitle());
            articleRespBody.setAuthor(articleDTO.getAuthor());
            articleRespBody.setContent(articleDTO.getContent());
            articleRespBody.setPic(articleDTO.getPic());
            articleRespBody.setReviewNum(articleDTO.getReviewNum());
            articleRespBody.setViewNum(articleDTO.getViewNum());
            articleRespBody.setCreateTime(TimeUtil.getTime(articleDTO.getCreateTime()));
            return articleRespBody;
        }).collect(Collectors.toList());
    }

    @Override
    public void articlePost(ArticlePost articlePost) {
        articlePost.setCreateTime(TimeUtil.getCurrentTime());
        ArticleExample example = new ArticleExample();
        example.createCriteria().andIdEqualTo(articlePost.getArticleId());
        Article article = new Article();
        article.setLastPostTime(TimeUtil.getCurrentTime());
        articleMapper.updateByExampleSelective(article, example);
        extMapper.updateArticlePostNum(articlePost.getArticleId());

        articlePostMapper.insertSelective(articlePost);
    }

    @Override
    public void articlePostReply(ArticlePostReply articlePostReply) {
        articlePostReply.setCreateTime(TimeUtil.getCurrentTime());
        ArticlePostExample example = new ArticlePostExample();
        example.createCriteria().andIdEqualTo(articlePostReply.getPostId());
        ArticlePost articlePost = new ArticlePost();
        articlePost.setLastReplyTime(TimeUtil.getCurrentTime());

        articlePostMapper.postReply(articlePostReply.getPostId());
        articlePostMapper.updateByExampleSelective(articlePost, example);

        articlePostReplyMapper.insertSelective(articlePostReply);
    }

    @Override
    public void insertArticle(Article article) {
        article.setCreateTime(TimeUtil.getCurrentTime());

        ArticleAuthorExample articleAuthorExample = new ArticleAuthorExample();
        articleAuthorExample.createCriteria().andAuthorNameEqualTo(article.getAuthorName());
        if(articleAuthorMapper.countByExample(articleAuthorExample) == 0){
            ArticleAuthor articleAuthor = new ArticleAuthor();
            articleAuthor.setAuthorName(article.getAuthorName());
            articleAuthor.setCreateTime(TimeUtil.getCurrentTime());
            articleAuthor.setArticleNum(0);
            articleAuthorMapper.insertSelective(articleAuthor);
        }
        articleAuthorMapper.changeArticleNum(article.getAuthorName());

        articleMapper.insertSelective(article);

    }

    @Override
    public Integer getSortIdByName(String sortName) {
        ArticleSortExample articleSortExample = new ArticleSortExample();
        articleSortExample.createCriteria().andSortNameEqualTo(sortName);
        return articleSortMapper.selectByExample(articleSortExample).get(0).getId();
    }
}
