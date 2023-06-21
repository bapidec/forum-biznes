package com.example.forumbiznes.Model;

import jakarta.persistence.*;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="Topics")
public class Topic extends AbstractModel{
    @ManyToMany
    private List<User> followers = new LinkedList<>();
    @Column(name = "title")
    private String title;
    @OneToMany(mappedBy="topic")
    private List<Post> posts = new LinkedList<>();

    public Topic(List<User> followers, String title, List<Post> posts) {
        this.followers = followers;
        this.title = title;
        this.posts = posts;
    }

    public Topic(){

    }
    @Override
    public String toString() {
        return title;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }


}
