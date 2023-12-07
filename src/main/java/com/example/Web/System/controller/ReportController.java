package com.example.Web.System.controller;

import com.example.Web.System.entity.Report;
import com.example.Web.System.exception.NotFoundException;
import com.example.Web.System.service.ReportService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping(value = "/generate-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<ByteArrayResource> generateReportPDF(@RequestBody Report report) {
        if (report.getReportId() == null) {
            throw new IllegalArgumentException("Report ID cannot be null");
        }

        try {
            ByteArrayOutputStream outputStream = reportService.generatePDF(report);

            if (outputStream != null) {
                ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf")
                        .contentType(MediaType.APPLICATION_PDF)
                        .contentLength(outputStream.size())
                        .body(resource);
            } else {
                throw new NotFoundException("PDF generation failed");
            }
        } catch (DocumentException e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }
}
