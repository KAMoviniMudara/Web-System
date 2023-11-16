package com.example.Web.System.controller;

import com.example.Web.System.entity.PdfGenerator;
import com.example.Web.System.entity.Report;
import com.example.Web.System.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/api/reports")
    public ResponseEntity<String> addReport(@RequestBody Report report) {
        try {
            reportService.saveReport(report); // Save report details

            // Generate PDF based on the report data
            byte[] generatedPdf = PdfGenerator.generatePdf(report);

            // Save or send the generated PDF as required
            // ...

            return ResponseEntity.status(HttpStatus.OK)
                    .body("Report added successfully, PDF generated.");
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions properly
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding report or generating PDF.");
        }
    }
}
