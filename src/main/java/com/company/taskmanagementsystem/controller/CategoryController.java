package com.company.taskmanagementsystem.controller;

import com.company.taskmanagementsystem.model.request.CategoryRequest;
import com.company.taskmanagementsystem.model.response.CategoryResponse;
import com.company.taskmanagementsystem.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public void createCategory(@RequestBody CategoryRequest categoryRequest) {
        categoryService.createCategory(categoryRequest);
    }

    @GetMapping
    public List<CategoryResponse> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @GetMapping("/name")
    public CategoryResponse getCategoryByName(@RequestParam String name) {
        return categoryService.getCategoryByName(name);
    }

    @PutMapping("/{id}")
    public void updateCategory(@PathVariable Long id,
                               @RequestBody CategoryRequest categoryRequest) {
        categoryService.updateCategory(id, categoryRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
