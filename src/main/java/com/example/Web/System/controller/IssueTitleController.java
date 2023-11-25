package com.example.Web.System.controller;

import com.example.Web.System.dto.IssueTitleDTO;
import com.example.Web.System.service.IssueTitleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    @PatchMapping(path = "/deactivate-issueTitle-by-title")
    public ResponseEntity<String> deactivateIssueTitleByTitle(@Valid @RequestBody IssueTitleDTO issueTitleDTO, BindingResult result) {
        if (result.hasErrors()) {
            String errorMessages = extractErrorMessages(result);
            logger.error("Validation errors in DTO: {}", errorMessages);
            return ResponseEntity.badRequest().body("Validation errors: " + errorMessages);
        }

        try {
            String titleName = issueTitleDTO.getTitle();
            String deactivateStatus = issueTitleService.deactivateIssueTitleByTitle(titleName);
            return ResponseEntity.ok("Issue Title '" + titleName + "' deactivated: " + deactivateStatus);
        } catch (Exception e) {
            logger.error("Error deactivating issue title: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Error deactivating issue title: " + e.getMessage());
        }
    }


    @PutMapping("/update")
    public ResponseEntity<String> updateIssueTitle(@RequestBody IssueTitleDTO updatedIssueTitleDTO) {
        try {
            String updateStatus = issueTitleService.updateIssueTitleByTitle(updatedIssueTitleDTO);
            String title = updatedIssueTitleDTO.getTitle();

            if ("Title Updated".equals(updateStatus)) {
                logger.info("Issue Title '{}' updated successfully", title);
                return ResponseEntity.ok("Issue Title '" + title + "' updated successfully");
            } else if ("Title Not Found".equals(updateStatus)) {
                logger.error("Issue Title '{}' not found for update", title);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Issue Title '" + title + "' not found for update");
            } else {
                logger.error("Error updating issue title: {}", updateStatus);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating issue title: " + updateStatus);
            }
        } catch (Exception e) {
            logger.error("Error updating issue title: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating issue title: " + e.getMessage());
        }
    }


    private String extractErrorMessages(BindingResult result) {
        StringBuilder errorMessage = new StringBuilder();
        result.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append("; "));
        return errorMessage.toString();

    }


}
