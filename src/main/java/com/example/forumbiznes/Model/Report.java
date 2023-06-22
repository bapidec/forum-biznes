package com.example.forumbiznes.Model;

import jakarta.persistence.*;

@NamedQuery(
        name="showReportedPost",
        query="select p "
                + "from com.example.forumbiznes.Model.Post p "
                + "where p.reports = :report"
)
@Entity
@Table(name="Reports")
public class Report extends AbstractModel{
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Users")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Posts")
    private Post post;
    @Column(name = "reason")
    private String reason;
    @Column(name = "content")
    private String content;

    public Report(User user, Post post, String reason, String content) {
        this.user = user;
        this.reason = reason;
        this.content = content;
        this.post = post;
    }

    public Report(){

    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
