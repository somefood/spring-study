package com.somefood.board.domain.board;

import com.somefood.board.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByCategory(Category category);
}
