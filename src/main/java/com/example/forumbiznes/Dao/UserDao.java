package com.example.forumbiznes.Dao;

import com.example.forumbiznes.Model.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.List;
public class UserDao extends GenericDaoJpaImpl<User, Long>{
    public boolean isLoginOccupied(String login) {
        try{
            EntityManager em = getEntityManager();
            User user = em.createNamedQuery("isLoginOccupied", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
            em.close();
            return true;
        }catch(NoResultException e){
            return false;
        }
    }

    public User findUserByLoginAndPassword(String login, String password) {
        try{
            EntityManager em = getEntityManager();
            User user = em.createNamedQuery("findUserByLoginAndPassword", User.class)
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .getSingleResult();
            em.close();
            return user;
        }catch(NoResultException e){
            return null;
        }

    }
}
