package com.example.forumbiznes.Dao;

import com.example.forumbiznes.Model.Comment;
import jakarta.ejb.Stateless;

@Stateless
public class CommentDao extends GenericDaoJpaImpl<Comment, Long>{
    public CommentDao() {
        super(Comment.class);
    }
}
