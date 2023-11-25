package com.example.Web.System.service.impl;

import com.example.Web.System.entity.Category;
import com.example.Web.System.repository.CategoryRepository;
import com.example.Web.System.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    // Assuming autowired repository
    private CategoryRepository categoryRepository;

    @Autowired
    public void CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findCategoryById(Long categoryId) {
        // Implement logic to find Category by ID using the repository
        return categoryRepository.findById(categoryId).orElse(null);
    }


    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public String deactivateCategoryByName(String categoryName) {
        try {
            Category category = categoryRepository.findByCategoryName(categoryName);
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

    @Override
    public Category findCategoryById(Category categoryID) {
        return null;
    }
}