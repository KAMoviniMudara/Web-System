package com.example.Web.System.service.impl;

import com.example.Web.System.entity.Report;
import com.example.Web.System.exception.NotFoundException;
import com.example.Web.System.repository.ReportRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Optional;

@Service
public class ReportServiceImpl {

    private final ReportRepository reportRepository;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public ByteArrayOutputStream generatePDF(Report report) throws NotFoundException, DocumentException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, outputStream);

            document.open();

            Font timesRoman = new Font(Font.FontFamily.TIMES_ROMAN, 12);
            document.add(new Paragraph("Report Name: " + report.getReportName(), timesRoman));
            document.add(new Paragraph("Start Date: " + report.getStartDate(), timesRoman));
            document.add(new Paragraph("End Date: " + report.getEndDate(), timesRoman));

        } finally {
            if (document != null) {
                document.close();
            }
        }

        return outputStream;
    }


    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }


}
