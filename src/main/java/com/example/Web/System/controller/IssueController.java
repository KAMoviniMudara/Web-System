package com.example.Web.System.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Web.System.dto.IssueDTO;
import com.example.Web.System.service.IssueService;
import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IssueController.class);

    private final IssueService issueService;

    @Autowired
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addIssue(@RequestBody IssueDTO issueDTO) {
        try {
            issueService.addIssue(issueDTO);
            LOGGER.info("Issue added successfully");
            return new ResponseEntity<>("Issue added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.error("Error occurred while adding issue: {}", e.getMessage());
            return new ResponseEntity<>("Failed to add issue", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<IssueDTO>> getAllIssues() {
        try {
            List<IssueDTO> issues = issueService.getAllIssues();
            LOGGER.info("Retrieved all issues");
            return new ResponseEntity<>(issues, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Error occurred while retrieving issues: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
