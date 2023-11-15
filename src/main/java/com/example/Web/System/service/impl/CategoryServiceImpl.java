package com.example.Web.System.service.impl;

import com.example.Web.System.entity.Category;
import com.example.Web.System.repository.CategoryRepository;
import com.example.Web.System.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

        @Override
        public void deactivateCategoryByName(String categoryName) {
            try {
                Category existingCategory = categoryRepository.findByCategoryName(categoryName);
                if (existingCategory == null) {
                    throw new IllegalArgumentException("Category does not exist.");
                }

                existingCategory.setActiveState(false);
                categoryRepository.save(existingCategory);
            } catch (IllegalArgumentException e) {
                throw e;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Error deactivating category. Details: " + e.getMessage(), e);
            }
        }
    }








