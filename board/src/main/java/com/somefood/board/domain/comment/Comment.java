package com.somefood.board.domain.comment;

import com.somefood.board.domain.BaseTimeEntity;
import com.somefood.board.domain.board.Board;
import com.somefood.board.domain.category.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    public void setBoard(Board board) {
        if (this.board != null) { // 기존에 이미 팀이 존재한다면
            this.board.getComments().remove(this); // 관계를 끊는다.
        }
        this.board = board;
        board.getComments().add(this);
    }

    @Builder
    public Comment(String content, Board board) {
        this.content = content;
        this.board = board;
    }
}
