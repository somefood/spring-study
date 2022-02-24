package com.somefood.board.repository.category;

import com.somefood.board.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    public Category save(Category category);
    public Optional<Category> findById(Long id);
    public List<Category> findAll();
    public boolean remove(Long id);
}
