package com.somefood.boardproject.service.impl;

import com.somefood.boardproject.domain.board.Board;
import com.somefood.boardproject.domain.category.Category;
import com.somefood.boardproject.domain.category.CategoryType;
import com.somefood.boardproject.service.BoardService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Transactional
@SpringBootTest
class BoardServiceImplTest {

    @Autowired
    private BoardService boardService;

    @Test
    @DisplayName("게시글 추가")
    void addArticle() throws Exception {
        // given
        Category category = Category.builder().type(CategoryType.FREE).build();
        Board board = Board.builder().title("하이").content("바이").category(category).build();

        // when
        Board savedArticle = boardService.createBoard(board);
        Board findArticle = boardService.getBoard(savedArticle.getId());

        // then
        Assertions.assertThat(findArticle.getCategory().getType()).isEqualTo(category.getType());
    }

    @Test
    @DisplayName("Inherit 되는지 보기")
    void printInheritAnnotation() throws Exception {
        // given
        Class<? extends BoardService> aClass = boardService.getClass();

        // when
        Method[] declaredMethods = aClass.getMethods();
        for (Method declaredMethod : declaredMethods) {
            Annotation[] annotations = declaredMethod.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println("==================");
                System.out.println(annotation);
                System.out.println("==================");
            }
        }

        // then
    }
}