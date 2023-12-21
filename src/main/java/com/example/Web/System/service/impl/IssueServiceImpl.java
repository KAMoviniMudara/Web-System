package com.example.Web.System.service.impl;

import com.example.Web.System.dto.IssueDTO;
import com.example.Web.System.entity.Category;
import com.example.Web.System.entity.Issue;
import com.example.Web.System.entity.IssueTitle;
import com.example.Web.System.entity.User;
import com.example.Web.System.repository.CategoryRepository;
import com.example.Web.System.repository.IssueRepository;
import com.example.Web.System.repository.IssueTitleRepository;
import com.example.Web.System.repository.UserRepo;
import com.example.Web.System.service.IssueService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssueServiceImpl implements IssueService {
    private final IssueRepository issueRepository;
    private final CategoryRepository categoryRepository;
    private final IssueTitleRepository issueTitleRepository;
    private final UserRepo userRepo;

    @Autowired
    public IssueServiceImpl(IssueRepository issueRepository, CategoryRepository categoryRepository,
                            IssueTitleRepository issueTitleRepository, UserRepo userRepo) {
        this.issueRepository = issueRepository;
        this.categoryRepository = categoryRepository;
        this.issueTitleRepository = issueTitleRepository;
        this.userRepo = userRepo;
    }

    @Override
    public IssueDTO addIssue(IssueDTO issueDTO) {
        Issue issue = new Issue();
        Category category = categoryRepository.findById((long) issueDTO.getCategoryID()).orElse(null);
        IssueTitle issueTitle = issueTitleRepository.findById((long) issueDTO.getIssueTitleID()).orElse(null);
        User informedByUser = userRepo.findById(issueDTO.getInformedByUserID()).orElse(null);
        User actionTakenByUser = userRepo.findById(issueDTO.getActionTakenByUserID()).orElse(null);

        issue.setCategory(category);
        issue.setIssueTitle(issueTitle);
        issue.setLocation(issueDTO.getLocation());
        issue.setStartDate(issueDTO.getStartDate());
        issue.setEndDate(issueDTO.getEndDate());
        issue.setStartTime(issueDTO.getStartTime());
        issue.setEndTime(issueDTO.getEndTime());
        issue.setInformedByUser(informedByUser);
        issue.setActionTakenByUser(actionTakenByUser);
        issue.setDurationMillis(issueDTO.getDurationMillis());
        issue.setStatus(issueDTO.getStatus());

        issueRepository.save(issue);
        return issueDTO;
    }

    @Override
    public ByteArrayOutputStream generatePDFByCategoryAndDateRange(Long categoryId, String startDate, String endDate) throws DocumentException {
        List<Issue> issues = issueRepository.findAllByCategoryCategoryIDAndStartDateBetween(categoryId, startDate, endDate);
        return generatePDFFromIssueList(issues);
    }

    @Override
    public List<IssueDTO> getAllIssuesByCategoryID(Long categoryID) {
        return null;
    }

    private List<IssueDTO> mapIssueListToIssueDTOList(List<Issue> issues) {
        return issues.stream()
                .map(this::mapIssueToIssueDTO)
                .collect(Collectors.toList());
    }

    private IssueDTO mapIssueToIssueDTO(Issue issue) {
        IssueDTO issueDTO = new IssueDTO();
        issueDTO.setIssueID(issue.getIssueID());
        issueDTO.setCategoryID((int) Math.toIntExact(issue.getCategory().getCategoryID()));
        issueDTO.setIssueTitleID(Math.toIntExact(issue.getIssueTitle().getIssueTitleID()));
        issueDTO.setLocation(issue.getLocation());
        issueDTO.setStartDate(issue.getStartDate());
        issueDTO.setEndDate(issue.getEndDate());
        issueDTO.setStartTime(issue.getStartTime());
        issueDTO.setEndTime(issue.getEndTime());
        issueDTO.setInformedByUserID(issue.getInformedByUser().getUserId());
        issueDTO.setActionTakenByUserID(issue.getActionTakenByUser().getUserId());
        issueDTO.setDurationMillis(issue.getDurationMillis());
        issueDTO.setStatus(issue.getStatus());

        return issueDTO;
    }

    private ByteArrayOutputStream generatePDFFromIssueList(List<Issue> issues) throws DocumentException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();

        for (Issue issue : issues) {
            document.add(new Paragraph("Issue ID: " + issue.getIssueID()));
            document.add(new Paragraph("Category Name: " + issue.getCategory().getCategoryName()));
            document.add(new Paragraph("Issue Title: " + issue.getIssueTitle().getTitle()));

            document.add(new Paragraph("---------------------------------------------"));
        }

        document.close();
        return outputStream;
    }
}
