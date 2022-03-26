package com.somefood.boardproject.service.impl;

import com.somefood.boardproject.domain.category.Category;
import com.somefood.boardproject.domain.category.CategoryType;
import com.somefood.boardproject.service.CategoryService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    @DisplayName("보드 추가")
    void addBoard() throws Exception {
        // given
        Category category = Category.builder().type(CategoryType.NOTICE).build();

        // when
        Category savedCategory = categoryService.createCategory(category);
        System.out.println("savedBoard = " + savedCategory.getId());

        // then
        Assertions.assertThat(savedCategory.getType()).isEqualTo(category.getType());
    }
}