package com.example.Web.System.controller;

import com.example.Web.System.dto.IssueDTO;
import com.example.Web.System.service.IssueService;
import com.itextpdf.text.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;

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
    public ResponseEntity<String> getAllIssues() {
        try {
            // Your existing getAllIssues method implementation

            return new ResponseEntity<>("Retrieved all issues", HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Error occurred while retrieving issues: {}", e.getMessage());
            return new ResponseEntity<>("Failed to retrieve issues", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/generate-pdf")
    public ResponseEntity<ByteArrayResource> generatePdfForCategoryAndDateRange(
            @RequestParam Long categoryID,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        try {
            ByteArrayOutputStream outputStream = issueService.generatePDFByCategoryAndDateRange(categoryID, startDate, endDate);

            if (outputStream == null || outputStream.size() == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=issues.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(outputStream.size())
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);
        } catch (DocumentException e) {
            LOGGER.error("Error generating PDF: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
