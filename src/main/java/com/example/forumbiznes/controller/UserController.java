package com.example.forumbiznes.controller;

import com.example.forumbiznes.Model.User;
import com.example.forumbiznes.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

    @Named
    @SessionScoped
    public class UserController implements Serializable {
        @EJB
        private UserService userService;
        private List<User> users;
        @PostConstruct
        private void init() {
            this.users = userService.findAll();
        }

        public List<User> getUsers() {
            return this.users;
        }

        public void onRemoveUser(User u) {
            if(u.getAccessLevel()<2){
                try {
                    this.userService.delete(u);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                users.remove(u);
            }
        }

        public void setMod(User u){
            u.setAccessLevel(
                    u.getAccessLevel()==0 ? 1 : (u.getAccessLevel()==1 ? 0 : 2)
            );
            userService.updateUser(u);
        }

        public String getTextColor(User user){
            switch (user.getAccessLevel()){
                case 0: return "black";
                case 1: return "orange";
            }
            return "red";
        }
}
