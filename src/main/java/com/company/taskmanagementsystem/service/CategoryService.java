package com.company.taskmanagementsystem.service;

import com.company.taskmanagementsystem.entity.Category;
import com.company.taskmanagementsystem.exception.NotFoundException;
import com.company.taskmanagementsystem.mapper.CategoryMapper;
import com.company.taskmanagementsystem.model.request.CategoryRequest;
import com.company.taskmanagementsystem.model.response.CategoryResponse;
import com.company.taskmanagementsystem.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final TaskService taskService;
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public void createCategory(CategoryRequest categoryRequest) {
        Category category = categoryMapper.toEntity(categoryRequest);
        categoryRepository.save(category);
    }

    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toCategoryResponse(categories);
    }

    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found id " + id));
        return categoryMapper.toCategoryResponse(category);
    }

    public CategoryResponse getCategoryByName(String name) {
        Category category = categoryRepository.findByCategoryName(name)
                .orElseThrow(() -> new NotFoundException("Category not found name " + name));
        return categoryMapper.toCategoryResponse(category);
    }

    public void updateCategory(Long id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found id " + id));
        category.setName(categoryRequest.getName());
        categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found id " + id));
        taskService.deleteTaskByCategoryId(id);
        categoryRepository.delete(category);

    }
}
