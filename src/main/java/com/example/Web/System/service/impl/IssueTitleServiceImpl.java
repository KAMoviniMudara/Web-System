package com.example.Web.System.service.impl;

import com.example.Web.System.dto.IssueTitleDTO;
import com.example.Web.System.entity.Category;
import com.example.Web.System.entity.IssueTitle;
import com.example.Web.System.repository.CategoryRepository;
import com.example.Web.System.repository.IssueTitleRepository;
import com.example.Web.System.service.IssueTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueTitleServiceImpl implements IssueTitleService {

    private final IssueTitleRepository issueTitleRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public IssueTitleServiceImpl(IssueTitleRepository issueTitleRepository, CategoryRepository categoryRepository) {
        this.issueTitleRepository = issueTitleRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void addIssueTitle(IssueTitleDTO issueTitleDTO) {
        IssueTitle issueTitle = new IssueTitle();
        issueTitle.setTitle(issueTitleDTO.getTitle());
        issueTitle.setActiveState(true);

        if (issueTitleDTO.getCategoryID() != null) {
            // Fetch the category from the database by its ID
            Category category = categoryRepository.findById(issueTitleDTO.getCategoryID()).orElse(null);
            if (category != null) {
                issueTitle.setCategory(category);
            }
        }

        issueTitleRepository.save(issueTitle);
    }
}
