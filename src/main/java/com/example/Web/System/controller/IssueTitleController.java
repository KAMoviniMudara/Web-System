package com.example.Web.System.controller;

import com.example.Web.System.dto.IssueTitleDTO;
import com.example.Web.System.service.IssueTitleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/issue-titles")
public class IssueTitleController {

    private static final Logger logger = LoggerFactory.getLogger(IssueTitleController.class);

    private final IssueTitleService issueTitleService;

    @Autowired
    public IssueTitleController(IssueTitleService issueTitleService) {
        this.issueTitleService = issueTitleService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addIssueTitle(@RequestBody IssueTitleDTO issueTitleDTO) {
        try {
            issueTitleService.addIssueTitle(issueTitleDTO);
            String addedTitleName = issueTitleDTO.getTitle();
            logger.info("Issue Title '{}' added successfully", addedTitleName);
            return ResponseEntity.status(HttpStatus.CREATED).body("Issue Title '" + addedTitleName + "' added successfully");
        } catch (Exception e) {
            logger.error("Error adding issue title: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding issue title");
        }
    }
}
