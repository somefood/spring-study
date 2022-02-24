package com.somefood.board.repository.category;

import com.somefood.board.domain.Board;
import com.somefood.board.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class JpaCategoryRepository implements CategoryRepository {

    private final EntityManager em;

    @Override
    public Category save(Category category) {
        em.persist(category);
        return category;
    }

    @Override
    public Optional<Category> findById(Long id) {
        Category category = em.find(Category.class, id);
        return Optional.ofNullable(category);
    }

    @Override
    public List<Category> findAll() {
        return em.createQuery("select m from Category m", Category.class).getResultList();
    }

    @Override
    public boolean remove(Long id) {
        Optional<Category> category = findById(id);
        if (category.isPresent()) {
            em.remove(id);
            return true;
        } else {
            return false;
        }
    }
}
