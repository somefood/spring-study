package com.somefood.board.web;

import com.somefood.board.domain.board.Board;
import com.somefood.board.domain.board.BoardRepository;
import com.somefood.board.domain.category.Category;
import com.somefood.board.domain.category.CategoryRepository;
import com.somefood.board.domain.category.CategoryType;
import com.somefood.board.domain.comment.Comment;
import com.somefood.board.domain.comment.CommentRepository;
import com.somefood.board.web.dto.BoardDto;
import com.somefood.board.web.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Component
public class DataInit {

    private final CategoryRepository categoryRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @PostConstruct
    public void init() {
        Category category1 = Category.builder().type(CategoryType.NOTICE).color("#1f9dfe").description("공지입니다.").build();
        Category category2 = Category.builder().type(CategoryType.FREE).color("#fe9a1f").description("자유글입니다.").build();
        Category category3 = Category.builder().type(CategoryType.QUESTION).color("#1ffea9").description("질문글입니다.").build();

        BoardDto boardDto1 = BoardDto.builder().title("공지1").content("공지입니다.").build();
        BoardDto boardDto2 = BoardDto.builder().title("자유1").content("자유게시판입니다.").build();
        BoardDto boardDto3 = BoardDto.builder().title("질문1").content("질문게시판입니다.").build();

        Board board1 = boardDto1.toEntity();
        Board board2 = boardDto2.toEntity();
        Board board3 = boardDto3.toEntity();

        board1.setCategory(category1);
        board2.setCategory(category2);
        board3.setCategory(category3);

        CommentDto commentDto1 = CommentDto.builder().content("댓글1").build();
        CommentDto commentDto2 = CommentDto.builder().content("댓글2").build();
        CommentDto commentDto3 = CommentDto.builder().content("댓글3").build();

        Comment comment1 = commentDto1.toEntity();
        Comment comment2 = commentDto1.toEntity();
        Comment comment3 = commentDto1.toEntity();

        comment1.setBoard(board1);
        comment2.setBoard(board2);
        comment3.setBoard(board3);

        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);

        boardRepository.save(board1);
        boardRepository.save(board2);
        boardRepository.save(board3);

        commentRepository.save(comment1);
        commentRepository.save(comment2);
        commentRepository.save(comment3);
    }
}
