package com.example.Web.System.service;

import com.example.Web.System.dto.IssueDTO;
import com.example.Web.System.dto.ResolvedIssueDTO;

import java.util.List;

public interface IssueService {
    IssueDTO addIssue(IssueDTO issueDTO);

    List<IssueDTO> getAllIssues();

    void addResolvedIssue(IssueDTO resolvedIssueDTO);

    void addResolvedIssue(ResolvedIssueDTO resolvedIssueDTO);
}
