package com.somefood.boardproject.service;

import com.somefood.boardproject.domain.Board;
import com.somefood.boardproject.domain.Category;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category getCategory(Long id);

    Category createCategory(Category category);

    void removeCategory(Long id);
}
