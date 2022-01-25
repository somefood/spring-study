package com.somefood.board.repository;

import com.somefood.board.domain.Board;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardRepositoryImpl implements BoardRepository {

    private static final Map<Long, Board> boardMap = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Board save(Board board) {
        board.setId(++sequence);
        boardMap.put(board.getId(), board);
        return board;
    }

    @Override
    public Board findById(Long id) {
        return BoardRepositoryImpl.boardMap.get(id);
    }

    @Override
    public List<Board> findAll() {
        return new ArrayList<>(boardMap.values());
    }
}
