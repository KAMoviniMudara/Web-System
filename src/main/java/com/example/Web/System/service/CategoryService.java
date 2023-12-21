package com.example.Web.System.service;

import com.example.Web.System.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    Category addCategory(Category category);
    String deactivateCategoryByName(String categoryName);


    List<String> getAllCategoryNames();
}







