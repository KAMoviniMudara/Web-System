package com.example.Web.System.service;

import com.example.Web.System.entity.Report;
import com.itextpdf.text.DocumentException;

import java.io.ByteArrayOutputStream;
import java.util.Optional;

public interface ReportService {
    ByteArrayOutputStream generatePDF(Report report) throws DocumentException;
    Report saveReport(Report report);
    Optional<Report> getReportById(int reportId);
}
