package com.hippie.houzhidao.request;

/**
 * @author 39239
 * @Date 2019/5/4 16:03
 * @Package com.hippie.houzhidao.request
 * @Description:
 */

public class QaAnswerReplyRequestBody {
    private Integer answerId;
    private String content;
    private String userName;
    private String replyTo;

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }
}
