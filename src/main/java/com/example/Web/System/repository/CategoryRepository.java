package com.example.Web.System.repository;

import com.example.Web.System.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface CategoryRepository extends JpaRepository<Category , Long> {
    Category findByCategoryName(String categoryName);
}
