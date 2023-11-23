package com.example.Web.System.service;

import com.example.Web.System.dto.IssueTitleDTO;
import com.example.Web.System.entity.IssueTitle;

public interface IssueTitleService {
    IssueTitle addIssueTitle(IssueTitle issueTitle);

    IssueTitle updateIssueTitle(IssueTitle issueTitle);

    void deactivateIssueTitleByTitle(String title);


}
