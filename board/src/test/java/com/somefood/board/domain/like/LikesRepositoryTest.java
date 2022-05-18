package com.somefood.board.domain.like;

import com.somefood.board.domain.board.Board;
import com.somefood.board.domain.board.BoardRepository;
import com.somefood.board.web.dto.LikeCountDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class LikesRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private LikesRepository likesRepository;

    @Test
    @DisplayName("특정 보드 좋아요 갖고오기")
    void getLikeByBoard() throws Exception {
        // given
        Board board = boardRepository.findAll().get(0);

        // when
        List<Likes> likes = likesRepository.findAllByBoard(board);

        // then
        for (Likes like : likes) {
            System.out.println(like.getBoard().getTitle() + " " + like.getStatus());
        }
    }

    @Test
    @DisplayName("카운트 조회")
    void getCountLong() throws Exception {
        // given
        Board board = boardRepository.findAll().get(0);

        // when
        List<Long> likeDislikeCount = likesRepository.findLikeDislikeCountPrintLong(board);

        // then
        System.out.println(likeDislikeCount);
    }

    @Test
    @DisplayName("카운트 조회")
    void getCount() throws Exception {
        // given
        Board board = boardRepository.findAll().get(0);

        // when
        List<LikeCountDto> likeDislikeCount = likesRepository.findLikeDislikeCount(board);

        // then
        System.out.println(likeDislikeCount);
    }
}