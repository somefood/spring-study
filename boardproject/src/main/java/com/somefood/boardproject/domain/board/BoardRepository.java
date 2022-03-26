package com.somefood.boardproject.domain.board;

import com.somefood.boardproject.domain.board.Board;
import com.somefood.boardproject.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByCategory(Category category);
}
