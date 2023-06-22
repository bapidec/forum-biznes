package com.example.forumbiznes.controller;

import com.example.forumbiznes.Model.Post;
import com.example.forumbiznes.Model.Report;
import com.example.forumbiznes.Model.Topic;
import com.example.forumbiznes.Model.User;
import com.example.forumbiznes.service.PostService;
import com.example.forumbiznes.service.ReportService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;

import java.util.List;

public class ReportController {
    @EJB
    private ReportService reportService;
    @Inject
    PostController postController;

    private List<Report> reports;
    private Report editedReport;

    @PostConstruct
    private void init() {
        this.reports = reportService.findAll();
    }

    public List<Report> getReports() {
        return this.reports;
    }

    public void onAddReport() {
        this.editedReport = new Report();
    }

    public void onSaveReport(Post p, User u) {
        this.reports.add(this.editedReport);
        this.postController.addReport(p, u, this.editedReport);
        this.editedReport = null;
    }

    public void onRemoveReport(Report r) {
        this.reportService.delete(r);
        reports.remove(r);
    }

    public void onCancelReport() {
        this.editedReport = null;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }
}
