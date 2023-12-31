package com.example.Web.System.service.impl;

import com.example.Web.System.entity.Category;
import com.example.Web.System.repository.CategoryRepository;
import com.example.Web.System.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public void CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
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
    public List<String> getAllCategoryNames() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(Category::getCategoryName)
                .collect(Collectors.toList());
    }
}