package com.somefood.boardproject.service;

import com.somefood.boardproject.domain.Board;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BoardService {

    List<Board> findAll();

    Board getBoard(Long id);

    Board createBoard(Board board);

    void removeBoard(Long id);
}
