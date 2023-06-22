package com.example.forumbiznes.controller;

import com.example.forumbiznes.Model.Comment;
import com.example.forumbiznes.Model.Post;
import com.example.forumbiznes.Model.Topic;
import com.example.forumbiznes.Model.User;
import com.example.forumbiznes.service.PostService;
import com.example.forumbiznes.service.PostServiceImpl;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class PostController implements Serializable {
    @EJB
    private PostService postService;
  
    @Inject
    TopicController topicController;
    
    private List<Post> posts;
    private Post editedPost;
    private Post currentPost;

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

    public void onSavePost(Topic t, User u) {

        Post saved;

        // jeśli nowy, nie edytowany
        if(this.editedPost.getId() == null) {
            this.posts.add(this.editedPost);
            saved = postService.save(this.editedPost);
        }
        else {
            saved = postService.update(this.editedPost);
            // aktualizacja this.topics z bazą
            this.posts.replaceAll(p -> p != editedPost ? p : saved);
        }
        this.topicController.addPost(t, u, saved);

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

    public String goToPostPage(Post p) {
        this.currentPost = p;
        return "post?id=" + p.getId() + "&faces-redirect=true";
    }

    public Post getCurrentPost() {
        return currentPost;
    }

    public void setCurrentPost(Post currentPost) {
        this.currentPost = currentPost;
    }

    public void addComment(Post p, Comment c) {
        this.postService.addComment(p, c);
    }
}
