package com.hippie.houzhidao.service;

import com.hippie.houzhidao.domain.QaAnswer;
import com.hippie.houzhidao.domain.QaAnswerReply;
import com.hippie.houzhidao.respbody.QARespBody;


import java.util.List;

/**
 * @author 39239
 * @Date 2019/5/3 18:16
 * @Package com.hippie.houzhidao.service
 * @Description:
 */

public interface QAService {
    List<QARespBody> getAllList(int pageNum, int pageSize);
    List<QARespBody> getTopList(int pageNum, int pageSize);
    List<QARespBody> getDetail(String title, String authorName);
    void qaAnswer(QaAnswer qaAnswer);
    void qaAnswerReply(QaAnswerReply qaAnswerReply);
}
