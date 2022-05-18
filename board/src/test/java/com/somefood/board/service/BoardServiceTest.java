package com.somefood.board.service;

import com.somefood.board.domain.board.Board;
import com.somefood.board.web.dto.BasicResponseDto;
import com.somefood.board.web.dto.LikeCountDto;
import com.somefood.board.web.dto.request.VoteRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    void likeBoard() {
        VoteRequestDto vote = new VoteRequestDto("dislike");
        BasicResponseDto<List<Long>> result = boardService.likeBoard(7L, "user3", vote);
        System.out.println("결과" + result);

        Board board = boardService.findBoard(7L).get();
        List<LikeCountDto> likeCount = boardService.findLikeCount(board);
    }

    @Test
    void likeAlreadyUser() {
        VoteRequestDto vote = new VoteRequestDto("dislike");
        BasicResponseDto<List<Long>> result = boardService.likeBoard(7L, "user2", vote);
        System.out.println("결과" + result);

        Board board = boardService.findBoard(7L).get();
        List<LikeCountDto> likeCount = boardService.findLikeCount(board);
    }
}