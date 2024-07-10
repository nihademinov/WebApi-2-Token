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
        List<Category> categories = categoryManager.getAllCategories();
        return categories.stream()
                .map(user -> modelMapper.map(user, CategoryDto.class))
                .collect(Collectors.toList());
    }




    public void createCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        categoryManager.saveCategory(category);
    }

    public CategoryDto updateCategoy(Long id, CategoryDto categoryDto) {
        ModelMapper modelMapper = new ModelMapper();
        Optional<Category> categoryOptional = categoryManager.getCategoryById(id);

        if (categoryOptional.isEmpty()) {
            throw new RuntimeException("Category not found");
        }

        Category category = categoryOptional.get();
        modelMapper.map(categoryDto, category);

        categoryManager.saveCategory(category);
        return modelMapper.map(category, CategoryDto.class);
    }


    public void deleteCategoryById(Long userId) {
        Category category = categoryManager.getCategoryById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Category not found"));
        categoryManager.deleteCategoryById(category.getId());

    }
}
