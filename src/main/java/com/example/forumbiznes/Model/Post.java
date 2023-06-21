package com.example.forumbiznes.Model;

import jakarta.persistence.*;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="Posts")
public class Post extends AbstractModel{
    @Column(name="title")
    private String title;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Users")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Topics")
    private Topic topic;

    @OneToMany(mappedBy="post")
    private List<Report> reports = new LinkedList<>();

    public Post(){

    }

    public Post(String title, String content, User user, Topic topic) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.topic = topic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return title;
    }

}
