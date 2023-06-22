package com.example.forumbiznes.Dao;

import com.example.forumbiznes.Model.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.Optional;

@Stateless
public class UserDao extends GenericDaoJpaImpl<User, Long>{
    public UserDao() {
        super(User.class);
    }

    public boolean isLoginOccupied(String login) {
        try{
            em.createNamedQuery("findUserByLogin", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
            return true;
        }catch(NoResultException e){
            return false;
        }
    }

    public User findUserByLoginAndPassword(String login, String password) {
        try{
            User user = em.createNamedQuery("findUserByLoginAndPassword", User.class)
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .getSingleResult();
            return user;
        }catch(NoResultException e){
            return null;
        }
    }

    public Optional<User> findUserByLogin(String login) {
        try{
            User user = em.createNamedQuery("findUserByLogin", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
            return Optional.of(user);
        }catch(NoResultException e){
            return Optional.empty();
        }
    }
}
