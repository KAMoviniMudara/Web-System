package com.example.Web.System.entity;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PdfGenerator {
    public static byte[] generatePdf(Report report) throws DocumentException, IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();

        document.add(new Paragraph("Report ID: " + report.getReportID()));
        document.add(new Paragraph("Location: " + report.getLocation()));
        document.add(new Paragraph("From Date: " + report.getFromDate()));
        document.add(new Paragraph("To Date: " + report.getToDate()));
        document.add(new Paragraph("Report Category: " + report.getReportCategory()));
        document.add(new Paragraph("Report Description: " + report.getReportDescription()));

        document.close();

        return outputStream.toByteArray();
    }
}
