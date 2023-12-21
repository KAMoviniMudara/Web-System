package com.example.Web.System.service;

import com.example.Web.System.dto.IssueTitleDTO;

import java.util.List;

public interface IssueTitleService {
    void addIssueTitle(IssueTitleDTO issueTitleDTO);

    String deactivateIssueTitleByTitle(String title);

    String updateIssueTitleByTitle(IssueTitleDTO updatedIssueTitleDTO);

    List<String> getAllTitles();
}
