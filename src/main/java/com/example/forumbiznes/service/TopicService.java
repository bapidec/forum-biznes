package com.example.forumbiznes.service;

import com.example.forumbiznes.Model.Post;
import com.example.forumbiznes.Model.Topic;
import com.example.forumbiznes.Model.User;

import java.util.List;

public interface TopicService {

    List<Topic> findAll();

    void delete(Topic t);
    Topic save(Topic editedTopic);

    Topic update(Topic editedTopic);

    void addFollower(Topic t, User u);

    void addPost(Topic t, Post p);
}
