package com.example.Web.System.service.impl;

import com.example.Web.System.entity.Report;
import com.example.Web.System.repository.ReportRepository;
import com.example.Web.System.service.ReportService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public ByteArrayOutputStream generatePDF(Report report) throws DocumentException {
        return null;
    }

    @Override
    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public Optional<Report> getReportById(int reportId) {
        return Optional.empty();
    }

    @Override
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }
}
