package com.example.Web.System.controller;

import com.example.Web.System.entity.Issue;
import com.example.Web.System.entity.IssueTitle;
import com.example.Web.System.repo.IssueTitleRepo;
import com.example.Web.System.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/issues")
public class IssueController {
    @Autowired
    private IssueService issueService;

    @Autowired
    private IssueTitleRepo issueTitleRepo;


    @PostMapping("/add")
    public ResponseEntity<Issue> addIssue(@RequestBody Issue issue) {
        IssueTitle issueTitle = issueTitleRepo.findById(issue.getIssueTitle().getTitle());
        issue.setIssueTitle(issueTitle);

        Issue savedIssue = issueService.addIssue(issue);
        return new ResponseEntity<>(savedIssue, HttpStatus.CREATED);
    }


    @PutMapping("/{selectedIssueTitle}")
    public ResponseEntity<Issue> updateIssueTitle(@PathVariable String selectedIssueTitle, @RequestBody String newIssueTitle) {

        Issue issue = issueService.findIssueByTitle(selectedIssueTitle);

        if (issue == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        issue.setIssueTitle(newIssueTitle);
        Issue updatedIssue = issueService.updateIssue(issue);
        return new ResponseEntity<>(updatedIssue, HttpStatus.OK);
    }
}

