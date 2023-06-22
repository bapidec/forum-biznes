package com.example.forumbiznes.service;

import com.example.forumbiznes.Model.Comment;
import com.example.forumbiznes.Model.Post;
import com.example.forumbiznes.Model.Report;
import com.example.forumbiznes.Model.User;

import java.util.List;

public interface PostService {
    List<Post> findAll();

    void delete(Post p);
    Post save(Post editedPost);
    void report(Post p);
    void addComment(Post p, Comment c);
    Post update(Post editedPost);

    void addReport(Post p, User u, Report editedReport);
}
