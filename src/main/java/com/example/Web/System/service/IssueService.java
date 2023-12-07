package com.example.Web.System.service;

import com.example.Web.System.dto.IssueDTO;
import com.itextpdf.text.DocumentException;


import java.io.ByteArrayOutputStream;
import java.util.List;

public interface IssueService {
    IssueDTO addIssue(IssueDTO issueDTO);





    ByteArrayOutputStream generatePDFByCategoryAndDateRange(Long categoryID, String startDate, String endDate) throws DocumentException;

    List<IssueDTO> getAllIssuesByCategoryID(Long categoryID);
}