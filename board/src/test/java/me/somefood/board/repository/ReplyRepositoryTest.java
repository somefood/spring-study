package me.somefood.board.repository;

import me.somefood.board.entity.Board;
import me.somefood.board.entity.Member;
import me.somefood.board.entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyRepositoryTest {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    void insertBoards() {

        IntStream.rangeClosed(1, 300).forEach(i -> {

            long bno = (long) (Math.random() * 100) + 1;

            Board board = Board.builder().bno(bno).build();

            Reply reply = Reply.builder()
                    .text("Reply...." + i)
                    .board(board)
                    .replyer("guest")
                    .build();

            replyRepository.save(reply);
        });
    }

    @Test
    void readReply1() {
        // 참고되어있는거 다 갖고옴
        Optional<Reply> result = replyRepository.findById(1L);

        Reply reply = result.get(); // EAGER일때는 FK도 일단 다 갖고와버림

        System.out.println(reply);
        System.out.println(reply.getBoard());
    }

    @Test
    void testListByBoard() {
        List<Reply> replyList = replyRepository.getRepliesByBoardOrderByRno(Board.builder().bno(97L).build());

        replyList.forEach(System.out::println);
    }
}