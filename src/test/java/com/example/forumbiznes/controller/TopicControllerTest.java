package com.example.forumbiznes.controller;

import com.example.forumbiznes.Model.Topic;
import com.example.forumbiznes.Model.User;
import com.example.forumbiznes.service.TopicService;
import com.example.forumbiznes.controller.TopicController;
import com.example.forumbiznes.service.TopicServiceImpl;
import jakarta.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TopicControllerTest {

    private TopicService topicService;

    private TopicController topicController;

    @Before
    public void setUp() {
        topicService = new TopicServiceImpl();
        topicController = new TopicController();
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

    // Add more test methods for other functionalities of the TopicController class

}
