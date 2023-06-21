package com.example.forumbiznes.Dao;

import com.example.forumbiznes.Model.Comment;
import com.example.forumbiznes.Model.Post;
import jakarta.ejb.Stateless;
@Stateless
public class PostDao extends GenericDaoJpaImpl<Post, Long>{
    public PostDao() {
        super(Post.class);
    }
}
