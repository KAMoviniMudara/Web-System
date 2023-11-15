package com.example.Web.System.repository;

import com.example.Web.System.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {
}
