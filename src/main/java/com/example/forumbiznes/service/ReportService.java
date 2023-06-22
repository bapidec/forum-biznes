package com.example.forumbiznes.service;

import com.example.forumbiznes.Model.Post;
import com.example.forumbiznes.Model.Report;
import com.example.forumbiznes.Model.Topic;
import com.example.forumbiznes.Model.User;

import java.util.List;

public interface ReportService {
    List<Report> findAll();

    void delete(Report r);
    Post showReportedPost(Report r);
}
