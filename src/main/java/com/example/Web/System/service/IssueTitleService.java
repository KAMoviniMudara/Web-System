package com.example.Web.System.service;

import com.example.Web.System.entity.IssueTitle;

public interface IssueTitleService {
    IssueTitle addIssueTitle(String title, Long categoryId);

    IssueTitle updateIssueTitle(String currentTitle, String newTitle, Long newCategoryId);

    void deactivateIssueTitle(String title);
}
