package com.example.Web.System.controller;

import com.example.Web.System.entity.Report;
import com.example.Web.System.service.ReportService;
import com.example.Web.System.service.impl.ReportPDFGenerationService;
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

    private final ReportPDFGenerationService reportPDFGenerationService;
    private final ReportService reportService;

    @Autowired
    public ReportController(
            ReportPDFGenerationService reportPDFGenerationService,
            ReportService reportService
    ) {
        this.reportPDFGenerationService = reportPDFGenerationService;
        this.reportService = reportService;
    }

    @PostMapping(value = "/generate-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<ByteArrayResource> generateReportPDF(@RequestBody Report report) {

        try {
            ByteArrayOutputStream outputStream = reportPDFGenerationService.generatePDF(report);

            // Save report data to the database
            Report savedReport = reportService.saveReport(report);

            ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .contentLength(outputStream.size())
                    .body(resource);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }
}
