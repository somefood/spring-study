package com.somefood.boardproject.service.impl;

import com.somefood.boardproject.domain.Board;
import com.somefood.boardproject.repository.BoardRepository;
import com.somefood.boardproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    @Override
    public Board getBoard(Long id) {
        Optional<Board> optional = boardRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    @Transactional
    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    @Override
    @Transactional
    public void removeBoard(Long id) {
        Optional<Board> optional = boardRepository.findById(id);
        optional.ifPresent(boardRepository::delete);
    }
}
