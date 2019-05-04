package com.hippie.houzhidao.controller;

import com.hippie.houzhidao.domain.QaAnswer;
import com.hippie.houzhidao.domain.QaAnswerReply;
import com.hippie.houzhidao.request.QaAnswerReplyRequestBody;
import com.hippie.houzhidao.request.QaAnswerRequestBody;
import com.hippie.houzhidao.respbody.QARespBody;
import com.hippie.houzhidao.respbody.RootRespBody;
import com.hippie.houzhidao.service.QAService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 39239
 * @Date 2019/5/3 18:26
 * @Package com.hippie.houzhidao.controller
 * @Description:
 */

@RestController
@RequestMapping(path = "/qa")
@Api("问答讨论相关接口")
public class QAController {
    @Autowired
    private QAService qaService;

    @GetMapping(path = "/get/all")
    @ApiOperation("获取所有列表")
    public RootRespBody<List<QARespBody>> getAllList(@RequestParam int pageNum, @RequestParam int pageSize){
        return RootRespBody.success(qaService.getAllList(pageNum, pageSize));
    }

    @GetMapping(path = "/get/top")
    @ApiOperation("获取前十问答")
    public RootRespBody<List<QARespBody>> getTopList(@RequestParam int pageNum, @RequestParam int pageSize){
        return RootRespBody.success(qaService.getTopList(pageNum, pageSize));
    }

    @GetMapping(path = "/get/detail")
    @ApiOperation("获取问答详情")
    public RootRespBody<List<QARespBody>> getDetail(@RequestParam String title, @RequestParam String authorName){
        return RootRespBody.success(qaService.getDetail(title, authorName));
    }

    @PostMapping(path = "/answer")
    @ApiOperation("回答问题")
    public RootRespBody qaAnswer(@RequestBody QaAnswerRequestBody requestBody){
        if(requestBody.getQuestionId()== 0){
            return RootRespBody.failure(RootRespBody.Status.REQUEST_PARAMETER_ERROR, "问题id不能为空");
        }
        if("".equals(requestBody.getUserName()) || requestBody.getUserName() == null){
            return RootRespBody.failure(RootRespBody.Status.REQUEST_PARAMETER_ERROR, "用户名称不能为空");
        }
        if("".equals(requestBody.getContent()) || requestBody.getContent() == null){
            return RootRespBody.failure(RootRespBody.Status.REQUEST_PARAMETER_ERROR, "评论内容不能为空");
        }

        QaAnswer qaAnswer = new QaAnswer();
        qaAnswer.setQuestionId(requestBody.getQuestionId());
        qaAnswer.setContent(requestBody.getContent());
        qaAnswer.setUserName(requestBody.getUserName());

        qaService.qaAnswer(qaAnswer);
        return RootRespBody.success();
    }

    @PostMapping("/answer/reply")
    @ApiOperation("回复问题")
    public RootRespBody qaAnswerReply(@RequestBody QaAnswerReplyRequestBody replyRequestBody){
        if(replyRequestBody.getAnswerId() == 0){
            return RootRespBody.failure(RootRespBody.Status.REQUEST_PARAMETER_ERROR, "答案id不能为空");
        }
        if("".equals(replyRequestBody.getUserName()) || replyRequestBody.getUserName() == null){
            return RootRespBody.failure(RootRespBody.Status.REQUEST_PARAMETER_ERROR, "用户名称不能为空");
        }
        if("".equals(replyRequestBody.getReplyTo()) || replyRequestBody.getReplyTo() == null){
            return RootRespBody.failure(RootRespBody.Status.REQUEST_PARAMETER_ERROR, "回复用户不能为空");
        }
        if("".equals(replyRequestBody.getContent()) || replyRequestBody.getContent() == null){
            return RootRespBody.failure(RootRespBody.Status.REQUEST_PARAMETER_ERROR, "回复内容不能为空");
        }

        QaAnswerReply qaAnswerReply = new QaAnswerReply();
        qaAnswerReply.setContent(replyRequestBody.getContent());
        qaAnswerReply.setAnswerId(replyRequestBody.getAnswerId());
        qaAnswerReply.setUserName(replyRequestBody.getUserName());
        qaAnswerReply.setReplyToUser(replyRequestBody.getReplyTo());
        qaService.qaAnswerReply(qaAnswerReply);

        return RootRespBody.success();
    }
}
