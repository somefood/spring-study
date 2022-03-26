package com.somefood.boardproject.domain.category;

import com.somefood.boardproject.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoryByType(CategoryType categoryType);
}
