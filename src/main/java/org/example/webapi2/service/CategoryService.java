package org.example.webapi2.service;


import org.example.webapi2.api.dto.CategoryDto;
import org.example.webapi2.api.dto.ProductDto;
import org.example.webapi2.api.model.Category;
import org.example.webapi2.api.model.Product;
import org.example.webapi2.api.model.User;
import org.example.webapi2.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(user -> modelMapper.map(user, CategoryDto.class))
                .collect(Collectors.toList());
    }

    public CategoryDto getCategory(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        return modelMapper.map(category, CategoryDto.class);
    }


    public void createCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        categoryRepository.save(category);
    }

    public CategoryDto updateCategoy(Integer id, CategoryDto categoryDto) {
        ModelMapper modelMapper = new ModelMapper();
        Optional<Category> categoryOptional = categoryRepository.findById(Long.valueOf(id));

        if (categoryOptional.isEmpty()) {
            throw new RuntimeException("Category not found");
        }

        Category category = categoryOptional.get();
        modelMapper.map(categoryDto, category); // Map the fields from categoryDto to category

        Category updatedCategory = categoryRepository.save(category);
        return modelMapper.map(updatedCategory, CategoryDto.class);
    }


    public void deleteCategoryById(Integer userId) {
        Category category = categoryRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new UsernameNotFoundException("Category not found"));
        categoryRepository.delete(category);
        categoryRepository.save(category);

    }
}
