package com.example.Web.System.repository;

import com.example.Web.System.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findAllByCategoryCategoryIDAndStartDateBetween(
            Long categoryID,
            String startDate,
            String endDate
    );
}
