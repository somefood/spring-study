package com.somefood.boardproject.service.impl;

import com.somefood.boardproject.domain.Board;
import com.somefood.boardproject.repository.BoardRepository;
import com.somefood.boardproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public Board getBoard(Long id) {
        Optional<Board> optional = boardRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public void removeBoard(Long id) {
        Optional<Board> optional = boardRepository.findById(id);
        optional.ifPresent(boardRepository::delete);
    }
}
