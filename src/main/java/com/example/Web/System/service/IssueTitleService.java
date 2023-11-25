package com.example.Web.System.service;

import com.example.Web.System.dto.IssueTitleDTO;

public interface IssueTitleService {
    void addIssueTitle(IssueTitleDTO issueTitleDTO);

    String deactivateIssueTitleByTitle(String title);

    String updateIssueTitleByTitle(IssueTitleDTO updatedIssueTitleDTO);
}
