package com.hippie.houzhidao.request;

/**
 * @author 39239
 * @Date 2019/5/4 16:02
 * @Package com.hippie.houzhidao.request
 * @Description:
 */

public class QaAnswerRequestBody {
    private Integer questionId;
    private String userName;
    private String content;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
