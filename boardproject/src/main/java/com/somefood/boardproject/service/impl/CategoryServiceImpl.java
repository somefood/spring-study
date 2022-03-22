package com.somefood.boardproject.service.impl;

import com.somefood.boardproject.domain.Category;
import com.somefood.boardproject.repository.CategoryRepository;
import com.somefood.boardproject.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void removeCategory(Long id) {
        Category category = categoryRepository.findById(id).get();
        categoryRepository.delete(category);
    }
}
