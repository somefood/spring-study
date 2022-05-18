package com.somefood.board.domain.comment;

import com.somefood.board.domain.BaseTimeEntity;
import com.somefood.board.domain.board.Board;
import com.somefood.board.domain.category.Category;
import com.somefood.board.domain.user.Account;
import com.somefood.board.web.dto.CommentDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Account writer;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent")
    private List<Comment> child = new ArrayList<>();

    public void addChildComment(Comment child) {
        this.child.add(child);
        child.setParent(this);
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public void setBoard(Board board) {
        if (this.board != null) { // 기존에 이미 팀이 존재한다면
            this.board.getComments().remove(this); // 관계를 끊는다.
        }
        this.board = board;
        board.getComments().add(this);
    }

    public void setWriter(Account account) {
        this.writer = account;
        account.getComments().add(this);
    }

    @Builder
    public Comment(String content, Board board) {
        this.content = content;
        this.board = board;
    }

    public void update(CommentDto commentDto) {
        content = commentDto.getContent();
    }
}
