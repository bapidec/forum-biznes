package com.example.forumbiznes.Dao;

import com.example.forumbiznes.Model.Report;
import jakarta.ejb.Stateless;
@Stateless
public class ReportDao extends GenericDaoJpaImpl<Report, Long>{
    public ReportDao() {
        super(Report.class);
    }
}
