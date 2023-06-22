package com.example.forumbiznes.controller;

import com.example.forumbiznes.Model.Topic;
import com.example.forumbiznes.Model.User;
import com.example.forumbiznes.service.TopicService;
import com.example.forumbiznes.service.TopicServiceImpl;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named()
@ViewScoped
public class TopicController implements Serializable {
    @EJB
    private TopicService topicService;
    private List<Topic> topics;
    private Topic editedTopic;

    @PostConstruct
    private void init() {
        topicService.save(new Topic("dupa"));
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

        // jeśli add, nie edit
        if(this.editedTopic.getId() == null) {
            this.topics.add(this.editedTopic);
        }

        Topic saved = topicService.save(this.editedTopic);
        // aktualizacja z bazą
        this.topics.replaceAll(t -> t != editedTopic ? t : saved);

        this.editedTopic = null;

    }

    public void onRemoveTopic(Topic t) {
        this.topicService.delete(t);
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
}
