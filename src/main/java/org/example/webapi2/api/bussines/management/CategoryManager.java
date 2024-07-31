package org.example.webapi2.api.bussines.management;


import org.example.webapi2.api.dto.CategoryDto;
import org.example.webapi2.api.dto.UserDto;
import org.example.webapi2.api.model.Category;
import org.example.webapi2.api.model.User;
import org.example.webapi2.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CategoryManager {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public CategoryManager(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<Category> getCategoryById(long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(user -> modelMapper.map(user, CategoryDto.class))
                .collect(Collectors.toList());
    }

    public void deleteCategoryById(long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }


    public String updateCategory(Long id, CategoryDto categoryDto) {
        Category category = getCategory(id);

        modelMapper.map(categoryDto, category);
        categoryRepository.save(category);
        return "Category updated";
    }

    public Category getCategory(Long id) {

        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundExceptionManager("Category not found with id: " + id));
    }

}
