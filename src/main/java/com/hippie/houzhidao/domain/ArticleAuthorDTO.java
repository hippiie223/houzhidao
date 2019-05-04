package com.hippie.houzhidao.domain;

/**
 * @author 39239
 * @Date 2019/4/30 15:15
 * @Package com.hippie.houzhidao.domain
 * @Description:
 */

public class ArticleAuthorDTO {
    private Integer id;
    private String authorName;
    private Integer articleNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Integer getArticleNum() {
        return articleNum;
    }

    public void setArticleNum(Integer articleNum) {
        this.articleNum = articleNum;
    }
}
