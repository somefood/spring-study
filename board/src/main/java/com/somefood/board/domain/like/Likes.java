package com.somefood.board.domain.like;

import com.somefood.board.domain.board.Board;
import com.somefood.board.domain.user.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@ToString
@Entity
public class Likes {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    @ToString.Exclude
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private Account account;

    @Enumerated(value = EnumType.STRING)
    private LikeStatus status;

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    @Builder
    public Likes(Board board, Account account, LikeStatus status) {
        this.board = board;
        this.account = account;
        this.status = status;
    }

    public void setAccountBoard(Account account, Board board) {
        board.getLikes().add(this);
        account.getLikes().add(this);

        this.board = board;
        this.account = account;
    }
}
