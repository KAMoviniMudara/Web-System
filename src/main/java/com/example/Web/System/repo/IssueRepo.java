package com.example.Web.System.repo;

import com.example.Web.System.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface IssueRepo extends JpaRepository<Issue, Long> {
}
