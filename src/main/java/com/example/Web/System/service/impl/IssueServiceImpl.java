package com.example.Web.System.service.impl;

import com.example.Web.System.dto.IssueDTO;
import com.example.Web.System.entity.Issue;
import com.example.Web.System.repository.IssueRepository;
import com.example.Web.System.service.IssueService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public IssueServiceImpl(IssueRepository issueRepository, ModelMapper modelMapper) {
        this.issueRepository = issueRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public IssueDTO addIssue(IssueDTO issueDTO) {
        Issue issue = modelMapper.map(issueDTO, Issue.class);
        // Perform additional logic if required
        Issue savedIssue = issueRepository.save(issue);
        return modelMapper.map(savedIssue, IssueDTO.class);
    }
    // Other methods if needed
}
