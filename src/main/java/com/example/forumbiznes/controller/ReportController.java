package com.example.forumbiznes.controller;

import com.example.forumbiznes.Model.Post;
import com.example.forumbiznes.Model.Report;
import com.example.forumbiznes.Model.User;
import com.example.forumbiznes.service.ReportService;
import com.example.forumbiznes.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;
@Named
@SessionScoped
public class ReportController implements Serializable {
    @EJB
    private ReportService reportService;
    private List<Report> reports;
    @PostConstruct
    private void init() {
        this.reports = reportService.findAll();
    }
    public List<Report> getReports() {
        return this.reports;
    }

    public void onRemoveReport(Report r) {
        try {
            this.reportService.delete(r);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        reports.remove(r);
    }

    public Post showReportedPost(Report r){
        return reportService.showReportedPost(r);
    }
}
