package com.example.Web.System.service;

import com.example.Web.System.dto.IssueDTO;


import java.util.List;

public interface IssueService {
    IssueDTO addIssue(IssueDTO issueDTO);

    List<IssueDTO> getAllIssues();




}
