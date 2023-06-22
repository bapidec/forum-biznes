package com.example.forumbiznes.Dao;

import com.example.forumbiznes.Model.Post;
import com.example.forumbiznes.Model.Report;
import com.example.forumbiznes.Model.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;

import java.util.Optional;

@Stateless
public class ReportDao extends GenericDaoJpaImpl<Report, Long>{
    public ReportDao() {
        super(Report.class);
    }

    public Optional<Post> showReportedPost(Report r) {
        try{
            Post post = em.createNamedQuery("showReportedPost", Post.class)
                    .setParameter("report", r)
                    .getSingleResult();
            return Optional.of(post);
        }catch(NoResultException e){
            return Optional.empty();
        }
    }
}
