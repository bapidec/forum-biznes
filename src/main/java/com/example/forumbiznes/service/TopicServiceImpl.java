package com.example.forumbiznes.service;

import com.example.forumbiznes.Model.Topic;
import com.example.forumbiznes.Model.User;
import jakarta.ejb.Stateful;
import jakarta.ejb.Stateless;

import java.util.ArrayList;
import java.util.List;

@Stateful
public class TopicServiceImpl implements TopicService {
    List<Topic> topics = new ArrayList<>();
    @Override
    public List<Topic> findAll() {
        return topics;
    }

    @Override
    public void delete(Topic t) {
        topics.remove(t);
    }

    @Override
    public Topic save(Topic editedTopic) {
        topics.add(editedTopic);
        return editedTopic;
    }

    @Override
    public void addFollower(Topic t, User u) {

    }
}
