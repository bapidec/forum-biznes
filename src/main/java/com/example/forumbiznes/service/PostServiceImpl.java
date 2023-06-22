package com.example.forumbiznes.service;

import com.example.forumbiznes.Dao.PostDao;
import com.example.forumbiznes.Model.Comment;
import com.example.forumbiznes.Model.Post;
import com.example.forumbiznes.Model.Report;
import com.example.forumbiznes.Model.User;
import jakarta.ejb.EJB;

import java.util.List;

public class PostServiceImpl implements PostService{

    @EJB
    PostDao dao;

    @Override
    public List<Post> findAll() {
        return dao.findAll();
    }

    @Override
    public void delete(Post p) {
        dao.delete(p);
    }

    @Override
    public Post save(Post editedPost) {
        dao.save(editedPost);
        return editedPost;
    }

    @Override
    public void report(Post p) {

    }

    @Override
    public void addComment(Post p, Comment c) {
        dao.addComment(p, c);
    }

    @Override
    public Post update(Post editedPost) {
        dao.update(editedPost);
        return editedPost;
    }

    @Override
    public void addReport(Post p, User u, Report editedReport) {
        dao.addReport(p, u, editedReport);
    }
}
