package com.somefood.board.web.dto;

import com.somefood.board.domain.board.Board;
import com.somefood.board.domain.comment.Comment;
import lombok.Builder;
import lombok.Data;

@Data
public class CommentDto {

    private String content;
    private Board board;

    @Builder
    public CommentDto(String content, Board board) {
        this.content = content;
        this.board = board;
    }

    public Comment toEntity() {
        return Comment.builder()
                .content(getContent())
                .board(getBoard())
                .build();
    }
}
