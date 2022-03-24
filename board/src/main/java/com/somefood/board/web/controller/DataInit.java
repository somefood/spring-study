package com.somefood.board.web.controller;

import com.somefood.board.domain.board.Board;
import com.somefood.board.domain.board.BoardRepository;
import com.somefood.board.domain.category.Category;
import com.somefood.board.domain.category.CategoryRepository;
import com.somefood.board.domain.category.CategoryType;
import com.somefood.board.web.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Component
public class DataInit {

    private final CategoryRepository categoryRepository;
    private final BoardRepository boardRepository;

    @PostConstruct
    public void init() {
        Category category1 = Category.builder().type(CategoryType.NOTICE).build();
        Category category2 = Category.builder().type(CategoryType.FREE).build();
        Category category3 = Category.builder().type(CategoryType.QUESTION).build();

        BoardDto boardDto1 = BoardDto.builder().title("헬로1").content("헬로1").build();
        BoardDto boardDto2 = BoardDto.builder().title("헬로2").content("헬로2").build();
        BoardDto boardDto3 = BoardDto.builder().title("헬로3").content("헬로3").build();

        Board board1 = boardDto1.toEntity();
        Board board2 = boardDto2.toEntity();
        Board board3 = boardDto3.toEntity();

        board1.setCategory(category1);
        board2.setCategory(category2);
        board3.setCategory(category3);

        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);

        boardRepository.save(board1);
        boardRepository.save(board2);
        boardRepository.save(board3);
    }

}
