package com.example.Web.System.service.impl;

import com.example.Web.System.entity.Report;
import com.example.Web.System.repository.ReportRepository;
import com.example.Web.System.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public void saveReport(Report report) {
        // Implement the logic to save the report to the database
        reportRepository.save(report);
    }

    // Other implementations as needed...
}
