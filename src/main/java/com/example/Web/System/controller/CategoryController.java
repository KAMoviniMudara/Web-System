package com.example.Web.System.controller;

import com.example.Web.System.entity.Category;
import com.example.Web.System.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        Category addedCategory = categoryService.addCategory(category);
        return ResponseEntity.ok(addedCategory);
    }

    @PutMapping("/deactivate/{categoryName}")
    public ResponseEntity<String> deactivateCategory(@PathVariable String categoryName) {
        try {
            categoryService.deactivateCategoryByName(categoryName);
            return ResponseEntity.ok("Category deactivated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deactivating category. Details: " + e.getMessage());
        }
    }
}
