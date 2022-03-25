package com.somefood.board.service;

import com.somefood.board.domain.category.Category;
import com.somefood.board.domain.category.CategoryRepository;
import com.somefood.board.domain.category.CategoryType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    Category findCategoryByType(CategoryType categoryType) {
        return categoryRepository.findByType(categoryType);
    }
}
