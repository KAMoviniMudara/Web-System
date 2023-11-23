package com.example.Web.System.controller;

import com.example.Web.System.dto.IssueTitleDTO;
import com.example.Web.System.service.IssueTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Web.System.entity.IssueTitle;
import com.example.Web.System.service.IssueTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/issue-titles")
public class IssueTitleController {

    private final IssueTitleService issueTitleService;

    @Autowired
    public IssueTitleController(IssueTitleService issueTitleService) {
        this.issueTitleService = issueTitleService;
    }

    @PostMapping("/add")
    public ResponseEntity<IssueTitle> addIssueTitle(@RequestBody IssueTitle issueTitle) {
        IssueTitle addedIssueTitle = issueTitleService.addIssueTitle(issueTitle);
        return ResponseEntity.ok(addedIssueTitle);
    }

    @PutMapping("/update")
    public ResponseEntity<IssueTitle> updateIssueTitle(@RequestBody IssueTitle issueTitle) {
        IssueTitle updatedIssueTitle = issueTitleService.updateIssueTitle(issueTitle);
        return ResponseEntity.ok(updatedIssueTitle);
    }

    @PostMapping("/deactivate")
    public ResponseEntity<String> deactivateIssueTitle(@RequestBody String title) {
        issueTitleService.deactivateIssueTitleByTitle(title);
        return ResponseEntity.ok("Issue title deactivated successfully");
    }
}
