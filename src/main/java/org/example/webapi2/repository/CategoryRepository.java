package org.example.webapi2.repository;

import org.example.webapi2.api.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByCategoryName(String categoryName);

    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.products WHERE c.categoryId = :id")
    Category findCategoryWithProductsById(@Param("id") Long id);
}
