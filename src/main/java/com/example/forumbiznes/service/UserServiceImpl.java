package com.example.forumbiznes.service;

import com.example.forumbiznes.Dao.UserDao;
import com.example.forumbiznes.Model.User;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;

@Stateless
public class UserServiceImpl implements UserService {

    @EJB
    private UserDao userDao;

    @Override
    public User findByLogin(String login) {
        return userDao.findUserByLogin(login).orElse(null);
    }

    @Override
    public boolean verify(String login, String password) {
        User u = userDao.findUserByLogin(login).orElse(null);
        return u != null ? password.equals(u.getPassword()) : false;

    }
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(User u) {
        userDao.delete(u);
    }
}
