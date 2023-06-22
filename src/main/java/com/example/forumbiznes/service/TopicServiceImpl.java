package com.example.forumbiznes.service;

import com.example.forumbiznes.Dao.TopicDao;
import com.example.forumbiznes.Model.Post;
import com.example.forumbiznes.Model.Topic;
import com.example.forumbiznes.Model.User;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateful;
import jakarta.ejb.Stateless;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class TopicServiceImpl implements TopicService {

    @EJB
    TopicDao dao;


    @Override
    public List<Topic> findAll() {
        return dao.findAll();
    }

    @Override
    public void delete(Topic t) {
        dao.delete(t);
    }

    @Override
    public Topic save(Topic editedTopic){
        dao.save(editedTopic);
        return editedTopic;
    }

    @Override
    public Topic update(Topic editedTopic) {
        dao.update(editedTopic);
        return editedTopic;
    }

    @Override
    public void addFollower(Topic t, User u) {

    }

    @Override
    public void addPost(Topic t, User u, Post p) {
        dao.addPost(t, u, p);
    }
}
