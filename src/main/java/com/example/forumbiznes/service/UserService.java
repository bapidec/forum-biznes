package com.example.forumbiznes.service;

import com.example.forumbiznes.Model.User;

public interface UserService {
    public User findByLogin(String login);
    public boolean verify(String login, String password);
}
