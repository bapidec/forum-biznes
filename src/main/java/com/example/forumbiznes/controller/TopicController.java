package com.example.forumbiznes.controller;

import com.example.forumbiznes.Model.Post;
import com.example.forumbiznes.Model.Topic;
import com.example.forumbiznes.Model.User;
import com.example.forumbiznes.service.TopicService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class TopicController implements Serializable {
    @EJB
    private TopicService topicService;
    private List<Topic> topics;
    private Topic editedTopic;
    private Topic currentTopic;

    @PostConstruct
    private void init() {
        this.topics = topicService.findAll();
    }

    public List<Topic> getTopics() {
        return this.topics;
    }

    public void onAddTopic() {
        this.editedTopic = new Topic();
    }

    public void onEditTopic(Topic t) {
        this.editedTopic = t;
    }

    public void onSaveTopic() {

        Topic saved;

        // jeśli nowy, nie edytowany
        if(this.editedTopic.getId() == null) {
            this.topics.add(this.editedTopic);
            saved = topicService.save(this.editedTopic);
        }
        else {
            saved = topicService.update(this.editedTopic);
            // aktualizacja this.topics z bazą
            this.topics.replaceAll(t -> t != editedTopic ? t : saved);
        }

        this.editedTopic = null;

    }

    public void onRemoveTopic(Topic t) {
        try {
            this.topicService.delete(t);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        topics.remove(t);
    }

    public void onCancelTopic() {
        this.editedTopic = null;
    }

    public Topic getEditedTopic() {
        return editedTopic;
    }

    public void setEditedTopic(Topic editedTopic) {
        this.editedTopic = editedTopic;
    }

    public void onFollowTopic(Topic t, User u) {
        topicService.addFollower(t, u);
    }

    public Topic getCurrentTopic() {
        return currentTopic;
    }

    public void setCurrentTopic(Topic currentTopic) {
        this.currentTopic = currentTopic;
    }

    public boolean isFollowed(Topic t, User u) {
        return this.topicService.isFollowed(t, u);
    }

    public String goToTopicPage(Topic t) {
        this.currentTopic = t;
        return "topic?faces-redirect=true";
    }
    public void addPost(Topic t, User u, Post p) {
        this.topicService.addPost(t, u, p);
    }

}
