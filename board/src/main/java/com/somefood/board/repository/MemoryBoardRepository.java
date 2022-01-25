package com.somefood.board.repository;

import com.somefood.board.domain.Board;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryBoardRepository implements BoardRepository {

    private static final Map<Long, Board> boardMap = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Board save(Board board) {
        board.setId(++sequence);
        boardMap.put(board.getId(), board);
        return board;
    }

    @Override
    public Optional<Board> findById(Long id) {
        return Optional.ofNullable(boardMap.get(id));
    }

    @Override
    public List<Board> findAll() {
        return new ArrayList<>(boardMap.values());
    }

    @Override
    public void remove(Long id) {
        boardMap.remove(id);
    }
}
