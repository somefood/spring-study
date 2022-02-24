package com.somefood.board.repository.board;

import com.somefood.board.domain.Board;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    public Board save(Board board);
    public Optional<Board> findById(Long id);
    public List<Board> findAll();
    public boolean remove(Long id);
}
