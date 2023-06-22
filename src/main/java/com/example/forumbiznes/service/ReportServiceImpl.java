package com.example.forumbiznes.service;

import com.example.forumbiznes.Dao.ReportDao;
import com.example.forumbiznes.Model.Report;
import jakarta.ejb.EJB;

import java.util.List;

public class ReportServiceImpl implements ReportService {

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
    public Report save(Report editedReport) {
        dao.save(editedReport);
        return editedReport;
    }
}
