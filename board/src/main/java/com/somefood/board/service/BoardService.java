package com.somefood.board.service;

import com.somefood.board.domain.Board;
import com.somefood.board.repository.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Board addBoard(Board board) {
        return boardRepository.save(board);
    }

    public Optional<Board> findBoard(Long id) {
        return boardRepository.findById(id);
    }

    public List<Board> findBoardList() {
        return boardRepository.findAll();
    }

    public Board modifyBoard(Board board) {
        return boardRepository.save(board);
    }

    public boolean removeBoard(Long id) {
        return boardRepository.remove(id);
    }

//    public Board findByName(String name) {}
}
