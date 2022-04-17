package com.somefood.board.domain.board;

import com.somefood.board.domain.BaseTimeEntity;
import com.somefood.board.domain.category.Category;
import com.somefood.board.domain.comment.Comment;
import com.somefood.board.domain.user.Account;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@ToString
@Table(name = "boards")
@Entity
public class Board extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Lob
    private String content;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ToString.Exclude
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Comment> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Account writer;

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setCategory(Category category) {
        if (this.category != null) { // 기존에 이미 팀이 존재한다면
            this.category.getBoards().remove(this); // 관계를 끊는다.
        }
        this.category = category;
        category.getBoards().add(this);
    }

    public void setWriter(Account account) {
        this.writer = account;
//        account.getBoards().add(this);
    }

    @Builder
    public Board(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}


