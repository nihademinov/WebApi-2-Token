package org.example.webapi2.service;


import org.example.webapi2.api.bussines.management.CategoryManager;
import org.example.webapi2.api.dto.CategoryDto;
import org.example.webapi2.api.model.Category;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Category category = modelMapper.map(categoryDto, Category.class);
        categoryManager.saveCategory(category);
    }

    public String updateCategoy(Long id, CategoryDto categoryDto) {
        return categoryManager.updateCategory(id,categoryDto);
    }


    public void deleteCategoryById(Long userId) {
        Category category = categoryManager.getCategoryById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Category not found"));
        categoryManager.deleteCategoryById(category.getCategoryId());

    }
}
