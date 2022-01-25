package com.somefood.board.repository;

import com.somefood.board.domain.Board;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BoardRepository {
    public Board save(Board board);
    public Board findById(Long id);
    public List<Board> findAll();
}
