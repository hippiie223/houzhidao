package com.hippie.houzhidao.service.impl;

import com.github.pagehelper.PageHelper;
import com.hippie.houzhidao.domain.QaAnswer;
import com.hippie.houzhidao.domain.QaAnswerReply;
import com.hippie.houzhidao.domain.QaQuestion;
import com.hippie.houzhidao.domain.example.QaAnswerExample;
import com.hippie.houzhidao.domain.example.QaQuestionExample;
import com.hippie.houzhidao.mapper.ExtMapper;
import com.hippie.houzhidao.mapper.QaAnswerMapper;
import com.hippie.houzhidao.mapper.QaAnswerReplyMapper;
import com.hippie.houzhidao.mapper.QaQuestionMapper;
import com.hippie.houzhidao.respbody.QARespBody;
import com.hippie.houzhidao.service.QAService;
import com.hippie.houzhidao.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 39239
 * @Date 2019/5/3 18:17
 * @Package com.hippie.houzhidao.service.impl
 * @Description:
 */
@Service
public class QAServiceImpl implements QAService {
    @Autowired
    private ExtMapper extMapper;
    @Autowired
    private QaQuestionMapper qaQuestionMapper;
    @Autowired
    private QaAnswerMapper qaAnswerMapper;
    @Autowired
    private QaAnswerReplyMapper answerReplyMapper;
    @Override
    public List<QARespBody> getAllList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return extMapper.getQAAllList().parallelStream().map(qadto -> {
            QARespBody qaRespBody = new QARespBody();
            qaRespBody.setId(qadto.getId());
            qaRespBody.setTitle(qadto.getTitle());
            qaRespBody.setAuthor(qadto.getAuthor());
            qaRespBody.setContent(qadto.getContent());
            qaRespBody.setPic(qadto.getPic());
            qaRespBody.setReviewNum(qadto.getReviewNum());
            qaRespBody.setViewNum(qadto.getViewNum());
            qaRespBody.setCreateTime(TimeUtil.getTime(qadto.getCreateTime()));
            return qaRespBody;
        }).collect(Collectors.toList());
    }

    @Override
    public List<QARespBody> getTopList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return extMapper.getTopQAList().parallelStream().map(qadto -> {
            QARespBody qaRespBody = new QARespBody();
            qaRespBody.setId(qadto.getId());
            qaRespBody.setTitle(qadto.getTitle());
            qaRespBody.setAuthor(qadto.getAuthor());
            qaRespBody.setContent(qadto.getContent());
            qaRespBody.setPic(qadto.getPic());
            qaRespBody.setReviewNum(qadto.getReviewNum());
            qaRespBody.setViewNum(qadto.getViewNum());
            qaRespBody.setCreateTime(TimeUtil.getTime(qadto.getCreateTime()));
            return qaRespBody;
        }).collect(Collectors.toList());
    }

    @Override
    public List<QARespBody> getDetail(String title, String authorName) {
        return extMapper.getQADetail(title, authorName).parallelStream().map(qadto -> {
            QARespBody qaRespBody = new QARespBody();
            qaRespBody.setId(qadto.getId());
            qaRespBody.setTitle(qadto.getTitle());
            qaRespBody.setAuthor(qadto.getAuthor());
            qaRespBody.setContent(qadto.getContent());
            qaRespBody.setPic(qadto.getPic());
            qaRespBody.setReviewNum(qadto.getReviewNum());
            qaRespBody.setViewNum(qadto.getViewNum());
            qaRespBody.setCreateTime(TimeUtil.getTime(qadto.getCreateTime()));
            return qaRespBody;
        }).collect(Collectors.toList());
    }

    @Override
    public void qaAnswer(QaAnswer qaAnswer) {
        qaAnswer.setCreateTime(TimeUtil.getCurrentTime());

        QaQuestionExample qaQuestionExample = new QaQuestionExample();
        qaQuestionExample.createCriteria().andIdEqualTo(qaAnswer.getQuestionId());
        QaQuestion qaQuestion = new QaQuestion();
        qaQuestion.setLastAnwserTime(TimeUtil.getCurrentTime());
        qaQuestionMapper.updateByExampleSelective(qaQuestion, qaQuestionExample);
        extMapper.updateQAAnswerNum(qaAnswer.getQuestionId());

        qaAnswerMapper.insertSelective(qaAnswer);
    }

    @Override
    public void qaAnswerReply(QaAnswerReply qaAnswerReply) {
        qaAnswerReply.setCreateTime(TimeUtil.getCurrentTime());

        QaAnswerExample qaAnswerExample = new QaAnswerExample();
        qaAnswerExample.createCriteria().andIdEqualTo(qaAnswerReply.getAnswerId());
        QaAnswer qaAnswer = new QaAnswer();
        qaAnswer.setLastReplyTime(TimeUtil.getCurrentTime());
        qaAnswerMapper.updateByExampleSelective(qaAnswer, qaAnswerExample);
        extMapper.updateQAAnswerReplyNum(qaAnswerReply.getAnswerId());

        answerReplyMapper.insertSelective(qaAnswerReply);
    }
}
