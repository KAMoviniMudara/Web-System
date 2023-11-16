package com.example.Web.System.controller;

import com.example.Web.System.dto.IssueTitleDTO;
import com.example.Web.System.service.IssueTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/issuetitles")
public class IssueTitleController {

    @Autowired
    private IssueTitleService issueTitleService;

    @PostMapping("/add")
    public ResponseEntity<String> addIssueTitle(@RequestBody IssueTitleDTO issueTitleDTO) {
        issueTitleService.addIssueTitle(issueTitleDTO);
        return ResponseEntity.ok("Issue title added successfully.");
    }

    @PutMapping("/update/{title}")
    public ResponseEntity<String> updateIssueTitle(@PathVariable String title, @RequestBody IssueTitleDTO issueTitleDTO) {
        issueTitleService.updateIssueTitleByTitle(title, issueTitleDTO);
        return ResponseEntity.ok("Issue title updated successfully.");
    }

    @PutMapping("/deactivate/{title}")
    public ResponseEntity<String> deactivateIssueTitle(@PathVariable String title) {
        issueTitleService.deactivateIssueTitleByTitle(title);
        return ResponseEntity.ok("Issue title deactivated successfully.");
    }
}
