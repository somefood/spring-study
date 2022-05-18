package com.somefood.board.domain.user;

import com.somefood.board.domain.board.Board;
import com.somefood.board.domain.comment.Comment;
import com.somefood.board.domain.like.Likes;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private String role;

    @OneToMany(mappedBy = "account")
    private List<Likes> likes = new ArrayList<>();

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Account(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
