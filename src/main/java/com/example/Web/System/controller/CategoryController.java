package com.example.Web.System.controller;

import com.example.Web.System.entity.Category;
import com.example.Web.System.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        Category addedCategory = categoryService.addCategory(category);
        return ResponseEntity.ok(addedCategory);
    }

    @PatchMapping(path = "/deactivate-category-by-name")
    public String deactivateCategoryByName(@RequestBody Map<String, String> requestBody) {
        try {
            String categoryName = requestBody.get("categoryName");
            String deactivateStatus = categoryService.deactivateCategoryByName(categoryName);
            return deactivateStatus;
        } catch (Exception e) {
            return "Error deactivating category";
        }
    }
}
