package com.example.forumbiznes.service;

import com.example.forumbiznes.Model.Post;
import com.example.forumbiznes.Model.Topic;
import com.example.forumbiznes.Model.User;

import java.util.ArrayList;
import java.util.List;

public class TopicServiceTestImpl implements TopicService{

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
    public Topic update(Topic editedTopic) {
        topics.removeIf(t -> t.getId() == editedTopic.getId());
        topics.add(editedTopic);
        return editedTopic;
    }

    @Override
    public void addFollower(Topic t, User u) {
        t.getFollowers().add(u);
    }

    @Override
    public void addPost(Topic t, User u, Post p) {
        t.getPosts().add(p);
    }

    @Override
    public void unfollow(Topic t, User u) {
        t.getFollowers().remove(u);
    }
}
