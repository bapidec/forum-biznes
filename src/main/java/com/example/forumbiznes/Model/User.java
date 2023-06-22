package com.example.forumbiznes.Model;

import jakarta.persistence.*;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="Users")

@NamedQuery(
        name="findUserByLoginAndPassword",
        query="select user "
                + "from com.example.forumbiznes.Model.User user "
                + "where user.login=:login and user.password=:password"
)

@NamedQuery(
        name="findUserByLogin",
        query="select user "
                + "from com.example.forumbiznes.Model.User user "
                + "where user.login=:login"
)
public class User extends AbstractModel{
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="users_topics", joinColumns=@JoinColumn(name="topic_id"), inverseJoinColumns=@JoinColumn(name="user_id"))
    private List<Topic> followedTopics = new LinkedList<>();
    @OneToMany(mappedBy="user", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Post> posts = new LinkedList<>();

    @OneToMany(mappedBy="user", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Report> reports = new LinkedList<>();
    @OneToMany(mappedBy="user", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comment> comments = new LinkedList<>();
    @Column(name = "accessLevel")
    private int accessLevel;
    //0-user 1-moderator 2-administrator
    public User(){

    }
    public User(String login, String password, String email, int accessLevel) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.accessLevel = accessLevel;
    }
    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
        accessLevel = 0;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Topic> getTopics() {
        return followedTopics;
    }

    public void setTopics(List<Topic> followedTopics) {
        this.followedTopics = followedTopics;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    @Override
    public String toString() {
        return login;
    }
}
