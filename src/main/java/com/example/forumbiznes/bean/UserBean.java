package com.example.forumbiznes.bean;


import com.example.forumbiznes.Model.User;
import com.example.forumbiznes.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.SecurityContext;

import java.io.Serializable;
import java.security.Principal;

@Named
@SessionScoped
public class UserBean implements Serializable {

    @EJB
    private UserService userService;

    @Inject
    private SecurityContext securityContext;
    @Inject @ManagedProperty("#{user}")
    private User user;

    @PostConstruct
    private void init(){
        Principal principal = securityContext.getCallerPrincipal();
        this.user = userService.findByLogin(principal.getName());
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
