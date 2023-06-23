package com.example.forumbiznes.controller;

import com.example.forumbiznes.Model.Post;
import com.example.forumbiznes.Model.Report;
import com.example.forumbiznes.Model.User;
import com.example.forumbiznes.service.ReportService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;
@Named
@SessionScoped
public class ReportController implements Serializable {
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

    public void onRemoveReport(Report r) {
        try {
            this.reportService.delete(r);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        reports.remove(r);
    }

    public Post showReportedPost(Report r) {
        return reportService.showReportedPost(r);
    }

    public void onAddReport() {
        this.editedReport = new Report();
    }

    public void onSaveReport(Post p, User u) {
        this.reports.add(this.editedReport);
        this.postController.addReport(p, u, this.editedReport);
        this.editedReport = null;
    }

    public void onCancelReport() {
        this.editedReport = null;
    }

    public Report getEditedReport() {
        return editedReport;
    }

    public void setEditedReport(Report editedReport) {
        this.editedReport = editedReport;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }


}
