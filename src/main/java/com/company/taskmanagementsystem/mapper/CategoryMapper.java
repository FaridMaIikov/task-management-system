package com.company.taskmanagementsystem.mapper;

import com.company.taskmanagementsystem.entity.Category;
import com.company.taskmanagementsystem.model.request.CategoryRequest;
import com.company.taskmanagementsystem.model.response.CategoryResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    List<CategoryResponse> toCategoryResponse(List<Category>categories);
    CategoryResponse toCategoryResponse(Category category);
    Category toEntity(CategoryRequest categoryRequest);
}
