package com.hippie.houzhidao.service.impl;

import com.github.pagehelper.PageHelper;
import com.hippie.houzhidao.domain.Topic;
import com.hippie.houzhidao.domain.TopicPost;
import com.hippie.houzhidao.domain.TopicPostReply;
import com.hippie.houzhidao.domain.example.TopicExample;
import com.hippie.houzhidao.domain.example.TopicPostExample;
import com.hippie.houzhidao.mapper.ExtMapper;
import com.hippie.houzhidao.mapper.TopicMapper;
import com.hippie.houzhidao.mapper.TopicPostMapper;
import com.hippie.houzhidao.mapper.TopicPostReplyMapper;
import com.hippie.houzhidao.respbody.TopicRespBody;
import com.hippie.houzhidao.service.TopicService;
import com.hippie.houzhidao.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 39239
 * @Date 2019/4/30 23:04
 * @Package com.hippie.houzhidao.service.impl
 * @Description:
 */

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private ExtMapper extMapper;

    @Autowired
    private TopicPostMapper topicPostMapper;

    @Autowired
    private TopicPostReplyMapper topicPostReplyMapper;

    @Override
    public List<TopicRespBody> getAllTopic(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        return extMapper.getAllTopicList().parallelStream().map(topicDTO -> {
            TopicRespBody topicRespBody = new TopicRespBody();
            topicRespBody.setId(topicDTO.getId());
            topicRespBody.setTitle(topicDTO.getTitle());
            topicRespBody.setAuthor(topicDTO.getAuthor());
            topicRespBody.setContent(topicDTO.getContent());
            topicRespBody.setPic(topicDTO.getPic());
            topicRespBody.setReviewNum(topicDTO.getReviewNum());
            topicRespBody.setViewNum(topicDTO.getViewNum());
            topicRespBody.setCreateTime(TimeUtil.getTime(topicDTO.getCreateTime()));
            return topicRespBody;
        }).collect(Collectors.toList());
    }

    @Override
    public List<TopicRespBody> getTopTopic() {
        return extMapper.getTopTopicList().parallelStream().map(topicDTO -> {
            TopicRespBody topicRespBody = new TopicRespBody();
            topicRespBody.setId(topicDTO.getId());
            topicRespBody.setTitle(topicDTO.getTitle());
            topicRespBody.setAuthor(topicDTO.getAuthor());
            topicRespBody.setContent(topicDTO.getContent());
            topicRespBody.setPic(topicDTO.getPic());
            topicRespBody.setReviewNum(topicDTO.getReviewNum());
            topicRespBody.setViewNum(topicDTO.getViewNum());
            topicRespBody.setCreateTime(TimeUtil.getTime(topicDTO.getCreateTime()));
            return topicRespBody;
        }).collect(Collectors.toList());
    }

    @Override
    public List<TopicRespBody> getTopicDetail(String title, String authorName) {
        return extMapper.getTopicDetail(title, authorName).parallelStream().map(topicDTO -> {
            TopicRespBody topicRespBody = new TopicRespBody();
            topicRespBody.setId(topicDTO.getId());
            topicRespBody.setTitle(topicDTO.getTitle());
            topicRespBody.setAuthor(topicDTO.getAuthor());
            topicRespBody.setContent(topicDTO.getContent());
            topicRespBody.setPic(topicDTO.getPic());
            topicRespBody.setReviewNum(topicDTO.getReviewNum());
            topicRespBody.setViewNum(topicDTO.getViewNum());
            topicRespBody.setCreateTime(TimeUtil.getTime(topicDTO.getCreateTime()));
            return topicRespBody;
        }).collect(Collectors.toList());

    }

    @Override
    public void topicPost(TopicPost topicPost) {
        topicPost.setCreateTime(TimeUtil.getCurrentTime());

        TopicExample topicExample = new TopicExample();
        topicExample.createCriteria().andIdEqualTo(topicPost.getTopicId());
        Topic topic = new Topic();
        topic.setLastPostTime(TimeUtil.getCurrentTime());
        topicMapper.updateByExampleSelective(topic, topicExample);
        extMapper.updatePostNum(topicPost.getTopicId());

        topicPostMapper.insertSelective(topicPost);
    }

    @Override
    public void topicPostReply(TopicPostReply topicPostReply) {
        topicPostReply.setCreateTime(TimeUtil.getCurrentTime());

        TopicPostExample topicPostExample = new TopicPostExample();
        topicPostExample.createCriteria().andIdEqualTo(topicPostReply.getPostId());
        TopicPost topicPost = new TopicPost();
        topicPost.setLastReplyTime(TimeUtil.getCurrentTime());
        topicPostMapper.updateByExampleSelective(topicPost, topicPostExample);
        extMapper.updatePostReplyNum(topicPostReply.getPostId());

        topicPostReplyMapper.insertSelective(topicPostReply);
    }
}
