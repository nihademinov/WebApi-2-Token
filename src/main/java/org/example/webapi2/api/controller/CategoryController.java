package org.example.webapi2.api.controller;


import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.webapi2.api.dto.ResponseDto.CategoryDto;
import org.example.webapi2.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public List<CategoryDto> getAllCategory() {
        return categoryService.getAllCategories();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public String createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        categoryService.createCategory(categoryDto);
        return "Category successfully created";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{categoryId}")
    public String updateCategory(@Valid @PathVariable Long categoryId, @RequestBody CategoryDto categoryDto) {
        return categoryService.updateCategoy(categoryId, categoryDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{categoryId}")
    public String deleteCategory(@Valid  @PathVariable Long categoryId) {
        categoryService.deleteCategoryById(categoryId);
        return "Category successfully deleted";
    }
}
