package com.somefood.boardproject.service.impl;

import com.somefood.boardproject.domain.Article;
import com.somefood.boardproject.domain.Board;
import com.somefood.boardproject.domain.BoardType;
import com.somefood.boardproject.repository.ArticleRepository;
import com.somefood.boardproject.service.ArticleService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class ArticleServiceImplTest {

    @Autowired
    private ArticleService articleService;

    @Test
    @DisplayName("게시글 추가")
    void addArticle() throws Exception {
        // given
        Board board = Board.builder().type(BoardType.FREE).build();
        Article article = Article.builder().title("하이").content("바이").board(board).build();

        // when
        Article savedArticle = articleService.createArticle(article);
        Article findArticle = articleService.getArticle(savedArticle.getId());

        // then
        Assertions.assertThat(findArticle.getBoard().getType()).isEqualTo(board.getType());
    }
}