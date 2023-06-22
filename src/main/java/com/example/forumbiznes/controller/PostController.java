package com.example.forumbiznes.controller;

import com.example.forumbiznes.Model.Post;
import com.example.forumbiznes.service.PostService;
import com.example.forumbiznes.service.PostServiceImpl;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class PostController implements Serializable {
    @EJB
    private PostService postService;
    private List<Post> posts;
    private Post editedPost;

    @PostConstruct
    private void init() {
        this.posts = postService.findAll();
    }

    public List<Post> getPosts() {
        return this.posts;
    }

    public void onAddPost() {
        this.editedPost = new Post();
    }

    public void onEditPost(Post p) {
        this.editedPost = p;
    }

    public void onSavePost() {

        // jeśli add, nie edit
        if(this.editedPost.getId() == null) {
            this.posts.add(this.editedPost);
        }

        Post saved = postService.save(this.editedPost);
        // aktualizacja z bazą
        this.posts.replaceAll(t -> t != editedPost ? t : saved);

        this.editedPost = null;

    }

    public void onRemovePost(Post p) {
        this.postService.delete(p);
        posts.remove(p);
    }

    public void onCancelPost() {
        this.editedPost = null;
    }

    public void onReportPost(Post p) {
        postService.report(p);
    }

    public Post getEditedPost() {
        return editedPost;
    }

    public void setEditedPost(Post editedPost) {
        this.editedPost = editedPost;
    }

}
