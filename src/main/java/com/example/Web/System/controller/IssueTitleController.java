package com.example.Web.System.controller;

import com.example.Web.System.service.IssueTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/issue-titles")
public class IssueTitleController {
    @Autowired
    private IssueTitleService issueTitleService;

    @PostMapping("/add")
    public ResponseEntity<String> addIssueTitle(
            @RequestParam String title,
            @RequestParam Long categoryId
    ) {
        try {
            issueTitleService.addIssueTitle(title, categoryId);
            return ResponseEntity.ok("Issue title added successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateIssueTitle(
            @RequestParam String currentTitle,
            @RequestParam String newTitle,
            @RequestParam Long newCategoryId
    ) {
        try {
            issueTitleService.updateIssueTitle(currentTitle, newTitle, newCategoryId);
            return ResponseEntity.ok("Issue title updated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/deactivate")
    public ResponseEntity<String> deactivateIssueTitle(@RequestParam String title) {
        try {
            issueTitleService.deactivateIssueTitle(title);
            return ResponseEntity.ok("Issue title deactivated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
