package com.somefood.board.service;

import com.somefood.board.domain.board.Board;
import com.somefood.board.domain.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    public Optional<Board> findBoard(Long id) {
        return boardRepository.findById(id);
    }

    public List<Board> findBoardList() {
        return boardRepository.findAll();
    }

    @Transactional
    public Board modifyBoard(Board board) {
        return boardRepository.save(board);
    }

    @Transactional
    public void removeBoard(Long id) {
        Board board = findBoard(id).orElseGet(null);
        boardRepository.delete(board);
    }

//    public Board findByName(String name) {}
}
