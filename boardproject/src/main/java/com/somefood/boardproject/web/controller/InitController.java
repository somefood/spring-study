package com.somefood.boardproject.web.controller;

import com.somefood.boardproject.domain.account.Account;
import com.somefood.boardproject.domain.account.RoleType;
import com.somefood.boardproject.domain.board.Board;
import com.somefood.boardproject.domain.category.Category;
import com.somefood.boardproject.domain.category.CategoryType;
import com.somefood.boardproject.domain.account.UserRepository;
import com.somefood.boardproject.service.BoardService;
import com.somefood.boardproject.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Transactional
@RequiredArgsConstructor
@Component
public class InitController {

    private final PasswordEncoder passwordEncoder;
    private final CategoryService categoryService;
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

        Category category1 = Category.builder().type(CategoryType.NOTICE).color("#1f9dfe").description("공지입니다.").build();
        Category category2 = Category.builder().type(CategoryType.FREE).color("#fe9a1f").description("자유글입니다.").build();
        Category category3 = Category.builder().type(CategoryType.QUESTION).color("#1ffea9").description("질문글입니다.").build();
        Board board1 = Board.builder().title("공지1").content("공지입니다.").category(category1).account(account1).build();
        Board board2 = Board.builder().title("자유1").content("자유글입니다.").category(category2).account(account1).build();
        Board board3 = Board.builder().title("질문1").content("질문글입니다.").category(category3).account(account2).build();

        categoryService.createCategory(category1);
        categoryService.createCategory(category2);
        categoryService.createCategory(category3);
        boardService.createBoard(board1);
        boardService.createBoard(board2);
        boardService.createBoard(board3);
    }

    private void createAccount(Account account1, String user, String ROLE_USER) {
        account1.setUsername(user);
        account1.setPassword(passwordEncoder.encode("1234"));
        account1.setRole(ROLE_USER);
    }
}
