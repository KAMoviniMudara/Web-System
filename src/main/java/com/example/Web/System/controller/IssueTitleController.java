package com.example.Web.System.controller;

import com.example.Web.System.entity.Category;
import com.example.Web.System.entity.IssueTitle;
import com.example.Web.System.repo.CategoryRepo;
import com.example.Web.System.repo.IssueTitleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/issue-titles")
public class IssueTitleController {
    @Autowired
    private IssueTitleRepo issueTitleRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @PostMapping("/add")
    public ResponseEntity<String> addIssueTitle(@RequestBody IssueTitle issueTitle) {
        Category existingCategory = categoryRepo.findById(issueTitle.getCategory().getId()).orElse(null);
        if (existingCategory == null) {
            return ResponseEntity.badRequest().body("Category does not exist.");
        }

        issueTitle.setCategory(existingCategory);

        issueTitleRepo.save(issueTitle);
        return ResponseEntity.ok("Issue title added successfully.");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateIssueTitle(@RequestBody IssueTitle issueTitle) {
        Category existingCategory = categoryRepo.findById(issueTitle.getCategory().getId()).orElse(null);
        if (existingCategory == null) {
            return ResponseEntity.badRequest().body("Category does not exist.");
        }

        IssueTitle existingIssueTitle = issueTitleRepo.findById(issueTitle.getId()).orElse(null);
        if (existingIssueTitle == null) {
            return ResponseEntity.badRequest().body("Issue title does not exist.");
        }

        existingIssueTitle.setTitle(issueTitle.getTitle());
        existingIssueTitle.setCategory(existingCategory);

        issueTitleRepo.save(existingIssueTitle);
        return ResponseEntity.ok("Issue title updated successfully.");
    }

    @PutMapping("/deactivate")
    public ResponseEntity<String> deactivateIssueTitle(@RequestParam Long issueTitleId) {
        IssueTitle issueTitle = issueTitleRepo.findById(issueTitleId).orElse(null);
        if (issueTitle == null) {
            return ResponseEntity.badRequest().body("Issue title does not exist.");
        }

        issueTitle.setActiveState(false);
        issueTitleRepo.save(issueTitle);
        return ResponseEntity.ok("Issue title deactivated successfully.");
    }
}
