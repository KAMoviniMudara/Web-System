package com.example.Web.System.service.impl;

import com.example.Web.System.dto.IssueTitleDTO;
import com.example.Web.System.entity.Category;
import com.example.Web.System.entity.IssueTitle;
import com.example.Web.System.repository.CategoryRepository;
import com.example.Web.System.repository.IssueTitleRepository;
import com.example.Web.System.service.IssueTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
            Category category = categoryRepository.findById(issueTitleDTO.getCategoryID()).orElse(null);
            if (category != null) {
                issueTitle.setCategory(category);
            }
        }

        issueTitleRepository.save(issueTitle);
    }
    @Override
    public String deactivateIssueTitleByTitle(String title) {
        try {
            IssueTitle issueTitle = issueTitleRepository.findByTitle(title);
            if (issueTitle == null) {
                return "Title Not Found";
            }
            issueTitle.setActiveState(false);
            issueTitleRepository.save(issueTitle);
            return "Title Deactivated";
        } catch (Exception e) {
            return "Deactivation Failed";
        }
    }
    @Override
    public String updateIssueTitleByTitle(IssueTitleDTO updatedIssueTitleDTO) {
        try {
            IssueTitle issueTitle = issueTitleRepository.findByTitle(updatedIssueTitleDTO.getTitle());
            if (issueTitle == null) {
                return "Title Not Found";
            }

            issueTitle.setTitle(updatedIssueTitleDTO.getTitle());

            if (updatedIssueTitleDTO.getCategoryID() != null) {
                Category category = categoryRepository.findById(updatedIssueTitleDTO.getCategoryID()).orElse(null);
                if (category != null) {
                    issueTitle.setCategory(category);
                }
            }

            issueTitle.setActiveState(true);

            issueTitleRepository.save(issueTitle);
            return "Title Updated";
        } catch (Exception e) {
            return "Update Failed";
        }
    }
    @Override
    public List<String> getAllTitles() {
        List<IssueTitle> issueTitles = issueTitleRepository.findAll();
        return issueTitles.stream()
                .map(IssueTitle::getTitle)
                .collect(Collectors.toList());
    }


}
