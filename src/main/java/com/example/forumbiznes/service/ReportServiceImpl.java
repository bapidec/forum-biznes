package com.example.forumbiznes.service;

import com.example.forumbiznes.Dao.PostDao;
import com.example.forumbiznes.Dao.ReportDao;
import com.example.forumbiznes.Model.Post;
import com.example.forumbiznes.Model.Report;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;
@Stateless
public class ReportServiceImpl implements ReportService{

    @EJB
    ReportDao dao;

    @Override
    public List<Report> findAll() {
        return dao.findAll();
    }

    @Override
    public void delete(Report r) {
        dao.delete(r);
    }

    @Override
    public Post showReportedPost(Report r) {
        return dao.showReportedPost(r).orElse(null);
    }

    public Report save(Report editedReport) {
        dao.save(editedReport);
        return editedReport;
    }
}
