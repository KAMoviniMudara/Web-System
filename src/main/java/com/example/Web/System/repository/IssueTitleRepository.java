package com.example.Web.System.repository;

import com.example.Web.System.entity.IssueTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository

public interface IssueTitleRepository extends JpaRepository<IssueTitle, Long> {
    IssueTitle findByTitle(String title);
}
