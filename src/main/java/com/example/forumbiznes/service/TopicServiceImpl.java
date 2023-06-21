package com.example.forumbiznes.service;

import com.example.forumbiznes.Model.Topic;
import com.example.forumbiznes.Model.User;
import jakarta.ejb.Stateless;

import java.util.List;

@Stateless
public class TopicServiceImpl implements TopicService {
    @Override
    public List<Topic> findAll() {
        return null;
    }

    @Override
    public void delete(Topic t) {

    }

    @Override
    public Topic save(Topic editedTopic) {
        return null;
    }

    @Override
    public void addFollower(Topic t, User u) {

    }
}
