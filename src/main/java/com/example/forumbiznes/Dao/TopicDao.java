package com.example.forumbiznes.Dao;

import com.example.forumbiznes.Model.Post;
import com.example.forumbiznes.Model.Topic;
import com.example.forumbiznes.Model.User;
import jakarta.ejb.Stateless;
@Stateless
public class TopicDao extends GenericDaoJpaImpl<Topic, Long>{

    public TopicDao() {
        super(Topic.class);
    }

    public void addPost(Topic t, User u, Post p) {
        u.getPosts().add(p);
        p.setTopic(t);
        p.setUser(u);
        t.getPosts().add(p);
        em.merge(t);
    }
}
