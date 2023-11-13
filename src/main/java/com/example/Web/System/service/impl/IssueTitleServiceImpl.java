package com.example.Web.System.service.impl;

import com.example.Web.System.repo.CategoryRepo;
import com.example.Web.System.repo.IssueTitleRepo;
import com.example.Web.System.service.IssueTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueTitleServiceImpl implements IssueTitleService {
    @Autowired
    private IssueTitleRepo issueTitleRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public IssueTitle addIssueTitle(IssueTitle issueTitle) {
        Category existingCategory = categoryRepository.findById(issueTitle.getCategory().getId()).orElse(null);
        if (existingCategory == null) {
            throw new IllegalArgumentException("Category does not exist.");
        }

        issueTitle.setCategory(existingCategory);

        return issueTitleRepository.save(issueTitle);
    }

    @Override
    public IssueTitle updateIssueTitle(IssueTitle issueTitle) {

        Category existingCategory = categoryRepository.findById(issueTitle.getCategory().getId()).orElse(null);
        if (existingCategory == null) {
            throw new IllegalArgumentException("Category does not exist.");
        }


        IssueTitle existingIssueTitle = issueTitleRepository.findById(issueTitle.getId()).orElse(null);
        if (existingIssueTitle == null) {
            throw new IllegalArgumentException("Issue title does not exist.");
        }

        existingIssueTitle.setTitle(issueTitle.getTitle());
        existingIssueTitle.setCategory(existingCategory);

        return issueTitleRepository.save(existingIssueTitle);
    }

    @Override
    public void deactivateIssueTitle(Long issueTitleId) {

        IssueTitle issueTitle = issueTitleRepository.findById(issueTitleId).orElse(null);
        if (issueTitle == null) {
            throw new IllegalArgumentException("Issue title does not exist.");
        }

        issueTitle.setActiveState(false);
        issueTitleRepository.save(issueTitle);
    }
}
