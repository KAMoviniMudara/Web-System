package com.example.Web.System.controller;

import com.example.Web.System.dto.IssueDTO;
import com.example.Web.System.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    private final IssueService issueService;

    @Autowired
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @PostMapping("/add")
    public ResponseEntity<IssueDTO> addIssue(@RequestBody IssueDTO issueDTO) {
        IssueDTO addedIssue = issueService.addIssue(issueDTO);
        return new ResponseEntity<>(addedIssue, HttpStatus.CREATED);
    }
    // Other endpoints
}
