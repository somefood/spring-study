package com.somefood.boardproject.service;

import com.somefood.boardproject.domain.Board;

public interface BoardService {

    Board getBoard(Long id);

    Board createBoard(Board board);

    void removeBoard(Long id);
}
