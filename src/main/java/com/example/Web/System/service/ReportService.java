package com.example.Web.System.service;

import com.example.Web.System.entity.Report;
import com.example.Web.System.exception.NotFoundException;
import com.itextpdf.text.DocumentException;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

public interface ReportService {

    ByteArrayOutputStream generatePDF(Report report) throws NotFoundException, DocumentException;

    Report saveReport(Report report);

    Optional<Report> getReportById(Long reportId);

    List<Report> getAllReports();
}
