package org.example.webapi2.service;


import org.example.webapi2.api.bussines.management.CategoryManager;
import org.example.webapi2.api.dto.ResponseDto.CategoryDto;
import org.example.webapi2.api.model.Category;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryManager categoryManager;
    private final ModelMapper modelMapper = new ModelMapper();

    public CategoryService( CategoryManager categoryManager) {
        this.categoryManager = categoryManager;

    }

    public List<CategoryDto> getAllCategories() {

        return categoryManager.getAllCategories();
    }


    public void createCategory(CategoryDto categoryDto) {
        categoryManager.createCategory(categoryDto);
    }

    public String updateCategoy(Long id, CategoryDto categoryDto) {
        return categoryManager.updateCategory(id,categoryDto);
    }


    public void deleteCategoryById(Long userId) {
        Category category = categoryManager.getCategory(userId);

        categoryManager.deleteCategoryById(category.getCategoryId());

    }
}
