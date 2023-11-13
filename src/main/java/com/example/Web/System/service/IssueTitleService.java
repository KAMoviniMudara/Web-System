package com.example.Web.System.service;

public interface IssueTitleService {
    IssueTitle addIssueTitle(IssueTitle issueTitle);

    IssueTitle updateIssueTitle(IssueTitle issueTitle);

    void deactivateIssueTitle(Long issueTitleId);
}
