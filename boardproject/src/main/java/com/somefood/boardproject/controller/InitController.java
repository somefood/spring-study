package com.somefood.boardproject.controller;

import com.somefood.boardproject.domain.*;
import com.somefood.boardproject.repository.UserRepository;
import com.somefood.boardproject.service.ArticleService;
import com.somefood.boardproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Component
public class InitController {

    private final PasswordEncoder passwordEncoder;
    private final ArticleService articleService;
    private final BoardService boardService;
    private final UserRepository userRepository;

    public RoleType[] roleTypes() {
        return RoleType.values();
    }

    @PostConstruct
    public void init() {

        Account account1 = new Account();
        createAccount(account1, "user", "ROLE_USER");

        Account account2 = new Account();
        createAccount(account2, "manager", "ROLE_MANAGER");

        userRepository.save(account1);
        userRepository.save(account2);

        Board board1 = Board.builder().type(BoardType.FREE).build();
        Board board2 = Board.builder().type(BoardType.NOTICE).build();
        Board board3 = Board.builder().type(BoardType.QUESTION).build();
        Article article1 = Article.builder().title("하이1").content("바이1").board(board1).build();
        Article article2 = Article.builder().title("하이2").content("바이2").board(board2).build();
        Article article3 = Article.builder().title("하이3").content("바이3").board(board3).build();

        boardService.createBoard(board1);
        boardService.createBoard(board2);
        boardService.createBoard(board3);
        articleService.createArticle(article1);
        articleService.createArticle(article2);
        articleService.createArticle(article3);
    }

    private void createAccount(Account account1, String user, String ROLE_USER) {
        account1.setUsername(user);
        account1.setPassword(passwordEncoder.encode("1234"));
        account1.setRole(ROLE_USER);
    }
}
