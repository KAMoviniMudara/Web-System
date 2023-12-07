package com.example.Web.System.service.impl;

import com.example.Web.System.dto.IssueDTO;
import com.example.Web.System.entity.Report;
import com.example.Web.System.exception.NotFoundException;
import com.example.Web.System.service.IssueService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ReportPDFGenerationService {

    private final IssueService issueService;

    @Autowired
    public ReportPDFGenerationService(IssueService issueService) {
        this.issueService = issueService;
    }

    public ByteArrayOutputStream generatePDF(Report report) throws NotFoundException, DocumentException {
        // Assuming the Report class contains startDate, endDate, and categoryId
        List<IssueDTO> issues = issueService.getAllIssuesByCategoryID(report.getCategoryID().getCategoryID());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, outputStream);

            document.open();

            Font timesRoman = new Font(Font.FontFamily.TIMES_ROMAN, 12);
            document.add(new Paragraph("Report Name: " + report.getReportName(), timesRoman));
            document.add(new Paragraph("Start Date: " + report.getStartDate(), timesRoman));
            document.add(new Paragraph("End Date: " + report.getEndDate(), timesRoman));

            // Iterate through issues and add them to the PDF
            for (IssueDTO issue : issues) {
                // Add issue details to the PDF
                // Example: document.add(new Paragraph("Issue ID: " + issue.getIssueID(), timesRoman));
            }
        } finally {
            if (document != null) {
                document.close();
            }
        }

        return outputStream;
    }
}
