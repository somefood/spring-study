package com.somefood.board.web;

import com.somefood.board.domain.board.Board;
import com.somefood.board.domain.board.BoardRepository;
import com.somefood.board.domain.category.Category;
import com.somefood.board.domain.category.CategoryRepository;
import com.somefood.board.domain.category.CategoryType;
import com.somefood.board.domain.comment.Comment;
import com.somefood.board.domain.comment.CommentRepository;
import com.somefood.board.domain.user.Account;
import com.somefood.board.domain.user.UserRepository;
import com.somefood.board.web.dto.BoardDto;
import com.somefood.board.web.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Component
public class DataInit {

    private final CategoryRepository categoryRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        Category[] categories = new Category[3];
        categories[0] = categoryRepository.save(Category.builder().type(CategoryType.NOTICE).color("#1f9dfe").description("공지입니다.").build());
        categories[1] = categoryRepository.save(Category.builder().type(CategoryType.FREE).color("#fe9a1f").description("자유글입니다.").build());
        categories[2] = categoryRepository.save(Category.builder().type(CategoryType.QUESTION).color("#1ffea9").description("질문글입니다.").build());

        Account[] accounts = new Account[2];
        accounts[0] = Account.builder().username("user1").password(passwordEncoder.encode("1234"))
                .role("ROLE_USER").build();
        accounts[1] = Account.builder().username("user2").password(passwordEncoder.encode("1234"))
                .role("ROLE_USER").build();

        userRepository.save(accounts[0]);
        userRepository.save(accounts[1]);


        for (int i=0; i<50; i++) {
            Board board = Board.builder().title(categories[i%3].getType() + " 제목 " + i).content("내용 " + i).build();
            board.setCategory(categories[i%3]);
            board.setWriter(accounts[i%2]);
            boardRepository.save(board);
            for (int j=0; j<20; j++) {
                Comment comment = Comment.builder().content("댓글" + j).build();
                comment.setBoard(board);
                comment.setWriter(accounts[j%2]);
                commentRepository.save(comment);
            }
        }
    }
}
