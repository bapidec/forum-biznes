package com.example.forumbiznes.controller;

import com.example.forumbiznes.Model.Post;
import com.example.forumbiznes.Model.Topic;
import com.example.forumbiznes.Model.User;
import com.example.forumbiznes.service.TopicService;
import com.example.forumbiznes.service.TopicServiceTestImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TopicServiceTest {

    private TopicService topicService;

    @Before
    public void setUp() {
        topicService = new TopicServiceTestImpl();
        topicService.save(new Topic("Topic 1"));
        topicService.save(new Topic("Topic 2"));
    }

    @Test
    public void testGetTopics() {
        List<Topic> topics = topicService.findAll();
        assert topics.size() == 2;
    }

    @Test
    public void testSaveAndDeleteTopic() {

        Topic t = new Topic("Topic 3");

        topicService.save(t);
        List<Topic> topics = topicService.findAll();
        assert topics.size() == 3;

        topicService.delete(t);
        assert topics.size() == 2;

    }

    @Test
    public void testAddPost() {
        Topic t = new Topic("Topic with posts");
        User u = new User();

        assert t.getPosts().size() == 0;

        topicService.addPost(t, u, new Post());

        assert t.getPosts().size() == 1;
    }

    @Test
    public void testFollowAndUnfollow() {
        Topic t = new Topic("Followed topic");

        assert t.getFollowers().size() == 0;

        User u = new User();

        topicService.addFollower(t, u);
        assert t.getFollowers().size() == 1;

        topicService.unfollow(t, u);
        assert t.getFollowers().size() == 0;
    }



    // Add more test methods for other functionalities of the TopicController class

}
