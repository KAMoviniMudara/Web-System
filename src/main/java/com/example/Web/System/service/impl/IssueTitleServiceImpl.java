package com.example.Web.System.service.impl;

import com.example.Web.System.dto.IssueTitleDTO;
import com.example.Web.System.entity.IssueTitle;
import com.example.Web.System.repository.IssueTitleRepository;
import com.example.Web.System.service.IssueTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IssueTitleServiceImpl implements IssueTitleService {

    @Autowired
    private IssueTitleRepository issueTitleRepository;

    @Override
    public IssueTitleDTO addIssueTitle(IssueTitleDTO issueTitleDTO) {
        IssueTitle issueTitle = convertToEntity(issueTitleDTO);
        IssueTitle savedIssueTitle = issueTitleRepository.save(issueTitle);
        return convertToDTO(savedIssueTitle);
    }

    @Override
    public IssueTitleDTO updateIssueTitleByTitle(String title, IssueTitleDTO issueTitleDTO) {
        Optional<IssueTitle> existingIssueTitleOptional = issueTitleRepository.findByTitle(title);
        if (existingIssueTitleOptional.isPresent()) {
            IssueTitle existingIssueTitle = existingIssueTitleOptional.get();
            existingIssueTitle.setTitle(issueTitleDTO.getTitle());
            existingIssueTitle.setActiveState(issueTitleDTO.isActiveState());
            IssueTitle updatedIssueTitle = issueTitleRepository.save(existingIssueTitle);
            return convertToDTO(updatedIssueTitle);
        }
        return null; // Handle case where issue title with given title doesn't exist
    }

    @Override
    public void deactivateIssueTitleByTitle(String title) {
        Optional<IssueTitle> existingIssueTitleOptional = issueTitleRepository.findByTitle(title);
        existingIssueTitleOptional.ifPresent(existingIssueTitle -> {
            existingIssueTitle.setActiveState(false);
            issueTitleRepository.save(existingIssueTitle);
        });
    }

    @Override
    public IssueTitleDTO updateIssueTitle(String title, IssueTitleDTO issueTitleDTO) {
        return null;
    }

    @Override
    public void deactivateIssueTitle(String title) {

    }

    // Other methods...

    private IssueTitle convertToEntity(IssueTitleDTO issueTitleDTO) {
        IssueTitle issueTitle = new IssueTitle();
        issueTitle.setTitle(issueTitleDTO.getTitle());
        issueTitle.setActiveState(issueTitleDTO.isActiveState());
        return issueTitle;
    }

    private IssueTitleDTO convertToDTO(IssueTitle issueTitle) {
        IssueTitleDTO issueTitleDTO = new IssueTitleDTO();
        issueTitleDTO.setTitle(issueTitle.getTitle());
        issueTitleDTO.setActiveState(issueTitle.isActiveState());
        return issueTitleDTO;
    }
}
