package com.hippie.houzhidao.domain;

import java.util.Date;

public class QaQuestion {
    private Integer id;

    private Integer qaSortId;

    private String title;

    private Integer answerNum;

    private Integer hits;

    private String authorName;

    private String imgUrl;

    private Date createTime;

    private Date updateTime;

    private Date lastAnwserTime;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQaSortId() {
        return qaSortId;
    }

    public void setQaSortId(Integer qaSortId) {
        this.qaSortId = qaSortId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(Integer answerNum) {
        this.answerNum = answerNum;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getLastAnwserTime() {
        return lastAnwserTime;
    }

    public void setLastAnwserTime(Date lastAnwserTime) {
        this.lastAnwserTime = lastAnwserTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}