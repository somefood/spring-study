package com.somefood.boardproject.repository;

import com.somefood.boardproject.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
