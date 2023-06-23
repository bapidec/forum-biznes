package com.example.forumbiznes.Dao;

import com.example.forumbiznes.Model.Post;
import com.example.forumbiznes.Model.Topic;
import com.example.forumbiznes.Model.User;
import jakarta.ejb.Stateless;

import java.util.List;

@Stateless
public class TopicDao extends GenericDaoJpaImpl<Topic, Long>{

    public TopicDao() {
        super(Topic.class);
    }

    public void addPost(Topic t, User u, Post p) {
        u.getPosts().add(p);
        t.getPosts().add(p);
        em.merge(t);
    }

    public void addFollower(Topic t, User u) {
        u.getTopics().add(t);
        t.getFollowers().add(u);
        em.merge(t);
    }

    public List<User> findFollowers(Topic t, User u) {
        return t.getFollowers();
    }

    public void unfollow(Topic t, User u) {
        u.getTopics().remove(t);
        t.getFollowers().remove(u);
        em.merge(t);
    }
}
