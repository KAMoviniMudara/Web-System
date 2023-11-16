package com.example.Web.System.service;

import com.example.Web.System.dto.IssueTitleDTO;

public interface IssueTitleService {
    IssueTitleDTO addIssueTitle(IssueTitleDTO issueTitleDTO);
    IssueTitleDTO updateIssueTitleByTitle(String title, IssueTitleDTO issueTitleDTO);
    void deactivateIssueTitleByTitle(String title);

    IssueTitleDTO updateIssueTitle(String title, IssueTitleDTO issueTitleDTO);


    void deactivateIssueTitle(String title);
}
