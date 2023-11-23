package com.example.Web.System.service.impl;

import com.example.Web.System.entity.IssueTitle;
import com.example.Web.System.repository.IssueTitleRepository;
import com.example.Web.System.service.IssueTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IssueTitleServiceImpl implements IssueTitleService {

    private final IssueTitleRepository issueTitleRepository;

    @Autowired
    public IssueTitleServiceImpl(IssueTitleRepository issueTitleRepository) {
        this.issueTitleRepository = issueTitleRepository;
    }

    @Override
    public IssueTitle addIssueTitle(IssueTitle issueTitle) {
        return issueTitleRepository.save(issueTitle);
    }

    @Override
    public IssueTitle updateIssueTitle(IssueTitle issueTitle) {
        // Implement update logic here
        return issueTitleRepository.save(issueTitle);
    }

    @Override
    public void deactivateIssueTitleByTitle(String title) {
        IssueTitle issueTitle = issueTitleRepository.findByTitle(title);
        if (issueTitle != null) {
            issueTitle.setActiveState(false);
            issueTitleRepository.save(issueTitle);
        }
    }
}
