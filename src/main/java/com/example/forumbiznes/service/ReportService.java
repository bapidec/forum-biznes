package com.example.forumbiznes.service;

import com.example.forumbiznes.Model.Comment;
import com.example.forumbiznes.Model.Report;

import java.util.List;

public interface ReportService {
    List<Report> findAll();
    void delete(Report r);
    Report save(Report editedReport);
}
