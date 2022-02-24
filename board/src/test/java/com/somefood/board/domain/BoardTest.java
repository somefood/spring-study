package com.somefood.board.domain;

import com.somefood.board.repository.board.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BoardTest {
    
    @Autowired
    private BoardRepository boardRepository;

    @Test
    @DisplayName("추가 시 시간 기록 되는지")
    void boardInsertTest() throws Exception {
        //given
        LocalDateTime now = LocalDateTime.now();
        boardRepository.save(new Board());

        //when
        List<Board> boards = boardRepository.findAll();

        //then
        Board board = boards.get(0);

        System.out.println(">>>>> createDate = "+board.getCreatedDate() + ", modifiedDate = "+board.getModifiedDate());

        assertThat(board.getCreatedDate()).isAfter(now);
        assertThat(board.getModifiedDate()).isAfter(now);
    }
}