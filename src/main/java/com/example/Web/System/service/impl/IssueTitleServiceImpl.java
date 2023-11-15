package com.example.Web.System.service.impl;

import com.example.Web.System.entity.Category;
import com.example.Web.System.entity.IssueTitle;
import com.example.Web.System.repository.CategoryRepository;
import com.example.Web.System.repository.IssueTitleRepository;
import com.example.Web.System.service.IssueTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class IssueTitleServiceImpl implements IssueTitleService {
    @Autowired
    private IssueTitleRepository issueTitleRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public IssueTitle addIssueTitle(String title, Long categoryId) {
        Category existingCategory = categoryRepository.findById(categoryId).orElse(null);
        if (existingCategory == null) {
            throw new IllegalArgumentException("Category does not exist.");
        }

        IssueTitle newIssueTitle = new IssueTitle();
        newIssueTitle.setTitle(title);
        newIssueTitle.setCategory(existingCategory);

        return issueTitleRepository.save(newIssueTitle);
    }

    @Override
    public IssueTitle updateIssueTitle(String currentTitle, String newTitle, Long newCategoryId) {
        Category newCategory = categoryRepository.findById(newCategoryId).orElse(null);
        if (newCategory == null) {
            throw new IllegalArgumentException("New category does not exist.");
        }

        IssueTitle existingIssueTitle = (IssueTitle) issueTitleRepository.findByTitle(currentTitle).orElse(null);
        if (existingIssueTitle == null) {
            throw new IllegalArgumentException("Issue title does not exist.");
        }

        existingIssueTitle.setTitle(newTitle);
        existingIssueTitle.setCategory(newCategory);

        return issueTitleRepository.save(existingIssueTitle);
    }

    @Override
    public void deactivateIssueTitle(String title) {
        IssueTitle issueTitle = (IssueTitle) issueTitleRepository.findByTitle(title).orElse(null);
        if (issueTitle == null) {
            throw new IllegalArgumentException("Issue title does not exist.");
        }

        issueTitle.setActiveState(false);
        issueTitleRepository.save(issueTitle);
    }
}