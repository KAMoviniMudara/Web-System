package com.example.Web.System.service.impl;

import com.example.Web.System.dto.IssueDTO;
import com.example.Web.System.entity.Category;
import com.example.Web.System.entity.Issue;
import com.example.Web.System.entity.IssueTitle;
import com.example.Web.System.entity.User;
import com.example.Web.System.entity.enums.StatusEnum;
import com.example.Web.System.repository.CategoryRepository;
import com.example.Web.System.repository.IssueRepository;
import com.example.Web.System.repository.IssueTitleRepository;
import com.example.Web.System.repository.UserRepo;
import com.example.Web.System.service.IssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssueServiceImpl implements IssueService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IssueServiceImpl.class);

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
        LOGGER.info("Adding new issue...");
        Issue issue = new Issue();

        Category category = categoryRepository.findById((long) issueDTO.getCategoryID()).orElse(null);
        IssueTitle issueTitle = issueTitleRepository.findById((long) issueDTO.getIssueTitleID()).orElse(null);
        User informedByUser = userRepo.findById(issueDTO.getInformedByUserID()).orElse(null);

        issue.setCategory(category);
        issue.setIssueTitle(issueTitle);
        issue.setLocation(issueDTO.getLocation());
        issue.setStartDate(issueDTO.getStartDate());
        issue.setEndDate(issueDTO.getEndDate());
        issue.setStartTime(issueDTO.getStartTime());
        issue.setEndTime(issueDTO.getEndTime());
        issue.setInformedByUser(informedByUser);
        issue.setDurationMillis(issueDTO.getDurationMillis());

        issueRepository.save(issue);
        LOGGER.info("Issue added successfully");
        return issueDTO;
    }

    @Override
    public List<IssueDTO> getAllIssues() {
        LOGGER.info("Retrieving all issues...");
        List<Issue> issues = issueRepository.findAll();
        LOGGER.info("Retrieved all issues");
        return mapIssueListToIssueDTOList(issues);
    }

    private List<IssueDTO> mapIssueListToIssueDTOList(List<Issue> issues) {
        return issues.stream()
                .map(this::mapIssueToIssueDTO)
                .collect(Collectors.toList());
    }

    private IssueDTO mapIssueToIssueDTO(Issue issue) {
        IssueDTO issueDTO = new IssueDTO();
        issueDTO.setIssueID(issue.getIssueID());
        issueDTO.setCategoryID(Math.toIntExact(issue.getCategory().getCategoryID()));
        issueDTO.setIssueTitleID(Math.toIntExact(issue.getIssueTitle().getIssueTitleID()));
        issueDTO.setLocation(issue.getLocation());
        issueDTO.setStartDate(issue.getStartDate());
        issueDTO.setEndDate(issue.getEndDate());
        issueDTO.setStartTime(issue.getStartTime());
        issueDTO.setEndTime(issue.getEndTime());
        issueDTO.setInformedByUserID(issue.getInformedByUser().getUserId());
        issueDTO.setDurationMillis(issue.getDurationMillis());

        return issueDTO;
    }
}
