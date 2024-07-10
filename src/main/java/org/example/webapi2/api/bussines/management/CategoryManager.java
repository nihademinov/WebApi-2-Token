package org.example.webapi2.api.bussines.management;


import org.example.webapi2.api.model.Category;
import org.example.webapi2.repository.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryManager {
    private final CategoryRepository categoryRepository;
    public CategoryManager(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<Category> getCategoryById(long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void deleteCategoryById(long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }
}
