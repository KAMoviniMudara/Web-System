package com.example.Web.System.controller;

import com.example.Web.System.dto.CategoryDTO;
import com.example.Web.System.entity.Category;
import com.example.Web.System.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@Valid @RequestBody CategoryDTO categoryDTO, BindingResult result) {
        if (result.hasErrors()) {
            String errorMessages = extractErrorMessages(result);
            logger.error("Validation errors in categoryDTO: {}", errorMessages);
            return ResponseEntity.badRequest().body("Validation errors: " + errorMessages);
        }

        Category category = mapDtoToEntity(categoryDTO);
        category.setActiveState(true);

        Category addedCategory = categoryService.addCategory(category);
        return ResponseEntity.ok("Category added successfully: " + addedCategory.getCategoryName());
    }

    @PatchMapping(path = "/deactivate-category-by-name")
    public ResponseEntity<String> deactivateCategoryByName(@Valid @RequestBody CategoryDTO categoryDTO, BindingResult result) {
        if (result.hasErrors()) {
            String errorMessages = extractErrorMessages(result);
            logger.error("Validation errors in categoryDTO: {}", errorMessages);
            return ResponseEntity.badRequest().body("Validation errors: " + errorMessages);
        }

        try {
            String categoryName = categoryDTO.getCategoryName();
            String deactivateStatus = categoryService.deactivateCategoryByName(categoryName);
            return ResponseEntity.ok("Category '" + categoryName + "' deactivated: " + deactivateStatus);
        } catch (Exception e) {
            logger.error("Error deactivating category: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Error deactivating category: " + e.getMessage());
        }
    }
    @GetMapping("/categoryNames")
    public ResponseEntity<List<String>> getAllCategoryNames() {
        List<String> categoryNames = categoryService.getAllCategoryNames();
        return ResponseEntity.ok(categoryNames);
    }

    private Category mapDtoToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setActiveState(true);
        return category;
    }

    private String extractErrorMessages(BindingResult result) {
        StringBuilder errorMessage = new StringBuilder();
        result.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append("; "));
        return errorMessage.toString();
    }
}
