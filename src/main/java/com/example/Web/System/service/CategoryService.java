package com.example.Web.System.service;

import com.example.Web.System.entity.Category;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    
    Category addCategory(Category category);

    String deactivateCategoryByName(String categoryName);

    Category findCategoryById(Category categoryID);
}


