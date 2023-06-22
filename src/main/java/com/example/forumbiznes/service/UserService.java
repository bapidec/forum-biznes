package com.example.forumbiznes.service;

import com.example.forumbiznes.Model.User;

import java.util.List;

public interface UserService {
    public User findByLogin(String login);
    public boolean verify(String login, String password);
    public List<User> findAll();
    public void updateUser(User user);
    void delete(User u);
}
