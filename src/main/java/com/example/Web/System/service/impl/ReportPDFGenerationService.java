package com.example.Web.System.service.impl;

import com.example.Web.System.entity.Report;
import com.example.Web.System.exception.NotFoundException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class ReportPDFGenerationService {

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
}
