package com.example.forumbiznes.controller;

import com.example.forumbiznes.Dao.UserDao;
import com.example.forumbiznes.Model.User;
import com.example.forumbiznes.bean.UserBean;
import com.example.forumbiznes.service.UserService;
import jakarta.annotation.ManagedBean;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serializable;

@Named
@ViewScoped
public class RegistrationController implements Serializable {

    @EJB
    private Pbkdf2PasswordHash pbkdf;
    @EJB
    UserDao dao;
    @Inject
    private ExternalContext externalContext;

    private String login;
    private String password;

    private String email;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String onRegister(){
        return "register";
    }

    public void registration(){
        User u = new User();
        u.setLogin("test");
        u.setPassword(pbkdf.generate("test".toCharArray()));
        u.setEmail("test@test.pl");
        u.setAccessLevel(0);
        dao.save(u);
//        externalContext.redirect(externalContext.getRequestContextPath()+"login.xhtml");
    }
}