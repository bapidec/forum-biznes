package com.example.forumbiznes.service;

import com.example.forumbiznes.Model.Post;
import java.util.List;

public interface PostService {
    List<Post> findAll();

    void delete(Post p);
    Post save(Post editedPost);
    void report(Post p);

}
