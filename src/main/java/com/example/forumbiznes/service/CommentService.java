package com.example.forumbiznes.service;

import com.example.forumbiznes.Model.Comment;
import com.example.forumbiznes.Model.Post;

import java.util.List;

public interface CommentService {
    List<Comment> findAll();
    void delete(Comment c);
    Comment save(Comment editedComment);
//    void report(Comment c);
    Comment update(Comment editedComment);
}
