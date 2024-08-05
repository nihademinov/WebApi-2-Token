package org.example.webapi2.api.bussines.management;


import lombok.RequiredArgsConstructor;
import org.example.webapi2.exceptionHandler.AlreadyExistsException;
import org.example.webapi2.exceptionHandler.NotFoundException;
import org.example.webapi2.api.dto.ResponseDto.CategoryDto;
import org.example.webapi2.api.model.Category;
import org.example.webapi2.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryManager {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper = new ModelMapper();


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
                .orElseThrow(() -> new NotFoundException("Category not found with id: " + id));
    }

    private boolean categoryExists(Category category) {
        return categoryRepository.findByCategoryName(category.getCategoryName()).isPresent();
    }

    public void createCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        if(categoryExists(category)) {
            throw new AlreadyExistsException("Category already exists");
        }
        saveCategory(category);
    }

}
