package com.example.Web.System.service.impl;

import com.example.Web.System.entity.Category;
import com.example.Web.System.repository.CategoryRepository;
import com.example.Web.System.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public String deactivateCategoryByName(String customerName) {
        try {
            Category category = categoryRepository.findByCategoryName(customerName);
            if (category == null) {
                return "Category Not Found";
            }
            category.setActiveState(false);
            categoryRepository.save(category);
            return "Category Deactivated";
        } catch (Exception e) {
            return "Deactivation Failed";
        }
    }
}