package com.example.forumbiznes.bean;


import com.example.forumbiznes.Dao.TopicDao;
import com.example.forumbiznes.Dao.UserDao;
import com.example.forumbiznes.Model.User;
import com.example.forumbiznes.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.SecurityContext;

import java.io.Serializable;
import java.security.Principal;
import java.util.Map;

@Named
@SessionScoped
public class UserBean implements Serializable {

    @EJB
    private UserService userService;

    @Inject
    private SecurityContext securityContext;

    @EJB
    private UserDao dao;
    private User user;

    @PostConstruct
    public void init() {
        user = dao.findUserByLogin(getLogin()).orElse(null);
    }

    public boolean isLogged() {
        return getLogin() != null;
    }

    public String getLogin() {
        if (user != null) {
            return user.getLogin();
        }

        Principal principal = securityContext.getCallerPrincipal();
        if (principal != null) {
            this.user = userService.findByLogin(principal.getName());
        }

        return user != null ? user.getLogin() : null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getAccessLevel(){
        return user.getAccessLevel();
    }
}
