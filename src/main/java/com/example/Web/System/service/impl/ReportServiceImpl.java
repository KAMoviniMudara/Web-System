package com.example.Web.System.service.impl;

import com.example.Web.System.entity.Category;
import com.example.Web.System.entity.Report;
import com.example.Web.System.exception.NotFoundException;
import com.example.Web.System.repository.ReportRepository;
import com.example.Web.System.service.ReportService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Date;
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
    public ByteArrayOutputStream generatePDF(Report report) throws NotFoundException, DocumentException {
        Long reportId = report.getReportId();
        if (reportId == null) {
            throw new IllegalArgumentException("Report ID cannot be null");
        }

        Optional<Report> optionalReport = reportRepository.findById(reportId);
        if (optionalReport.isPresent()) {
            Report existingReport = optionalReport.get();
            String reportName = existingReport.getReportName();
            Date startDate = existingReport.getStartDate();
            Date endDate = existingReport.getEndDate();
            Category categoryID = existingReport.getCategoryID();

            boolean invalidDateRange = startDate.compareTo(endDate) > 0;
            boolean invalidCategoryID = (categoryID == null) || (categoryID.getCategoryID() == null) || (categoryID.getCategoryID() <= 0);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);
            document.open();

            document.add(new Paragraph("Report Name: " + reportName));
            document.add(new Paragraph("Start Date: " + startDate));
            document.add(new Paragraph("End Date: " + endDate));
            document.add(new Paragraph("Category ID: " + categoryID));

            if (invalidDateRange) {
                document.add(new Paragraph("Invalid Date Range!"));
            }

            if (invalidCategoryID) {
                document.add(new Paragraph("Invalid Category ID!"));
            }

            document.close();
            return outputStream;
        } else {
            throw new NotFoundException("Report not found");
        }
    }

    @Override
    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public Optional<Report> getReportById(Long reportId) {
        return reportRepository.findById(reportId);
    }

    @Override
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }
}
