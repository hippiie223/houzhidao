package com.hippie.houzhidao.service;

import com.hippie.houzhidao.domain.TopicPost;
import com.hippie.houzhidao.domain.TopicPostReply;
import com.hippie.houzhidao.respbody.TopicRespBody;


import java.util.List;

/**
 * @author 39239
 * @Date 2019/4/30 23:04
 * @Package com.hippie.houzhidao.service
 * @Description:
 */

public interface TopicService {
    List<TopicRespBody> getAllTopic(int pageNum, int pageSize);
    List<TopicRespBody> getTopTopic();
    List<TopicRespBody> getTopicDetail(String title, String authorName);
    void topicPost(TopicPost topicPost);
    void topicPostReply(TopicPostReply topicPostReply);
}
