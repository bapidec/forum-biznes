package com.example.forumbiznes.service;

import com.example.forumbiznes.Dao.CommentDao;
import com.example.forumbiznes.Model.Comment;
import jakarta.ejb.EJB;

import java.util.List;

public class CommentServiceImpl implements CommentService{

    @EJB
    CommentDao dao;

    @Override
    public List<Comment> findAll() {
        return dao.findAll();
    }

    @Override
    public void delete(Comment c) {
        dao.delete(c);
    }

    @Override
    public Comment save(Comment editedComment) {
        dao.save(editedComment);
        return editedComment;
    }

    @Override
    public Comment update(Comment editedComment) {
        dao.update(editedComment);
        return editedComment;
    }
}
