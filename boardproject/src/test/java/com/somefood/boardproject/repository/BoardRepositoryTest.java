package com.somefood.boardproject.repository;

import com.somefood.boardproject.domain.board.Board;
import com.somefood.boardproject.domain.board.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    @DisplayName("게시글 출력")
    void printBoard() throws Exception {
        // given
        List<Board> all = boardRepository.findAll();

        // when
        System.out.println(all);
        for (Board board : all) {
            System.out.println(board.getTitle());
            System.out.println(board.getContent());
        }

        // then
    }

}