package com.example.forumbiznes.Dao;

import com.example.forumbiznes.Model.Comment;
import com.example.forumbiznes.Model.Post;
import com.example.forumbiznes.Model.Report;
import com.example.forumbiznes.Model.User;
import jakarta.ejb.Stateless;
@Stateless
public class PostDao extends GenericDaoJpaImpl<Post, Long>{
    public PostDao() {
        super(Post.class);
    }

    public void addComment(Post p, Comment c) {
        c.setPost(p);
        p.getComments().add(c);
        em.merge(p);
    }

    public void addReport(Post p, User u, Report r) {
        u.getReports().add(r);
        p.getReports().add(r);
        r.setPost(p);
        r.setUser(u);
        em.merge(r);
    }
}
