package com.example.forumbiznes.Dao;

import com.example.forumbiznes.Model.Post;
import com.example.forumbiznes.Model.Topic;
import jakarta.ejb.Stateless;
@Stateless
public class TopicDao extends GenericDaoJpaImpl<Topic, Long>{

    public TopicDao() {
        super(Topic.class);
    }

    public void addPost(Topic t, Post p) {
        p.setTopic(t);
        t.getPosts().add(p);
        em.merge(t);
    }
}
