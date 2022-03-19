package com.somefood.boardproject.service.impl;

import com.somefood.boardproject.domain.Board;
import com.somefood.boardproject.domain.BoardType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class BoardServiceImplTest {

    @Autowired
    private BoardServiceImpl boardService;

    @Test
    @DisplayName("보드 추가")
    void addBoard() throws Exception {
        // given
        Board board = Board.builder().type(BoardType.NOTICE).build();

        // when
        Board savedBoard = boardService.createBoard(board);
        System.out.println("savedBoard = " + savedBoard.getId());

        // then
        Assertions.assertThat(savedBoard.getType()).isEqualTo(board.getType());
    }
}