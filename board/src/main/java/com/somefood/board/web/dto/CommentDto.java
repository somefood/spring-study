package com.somefood.board.web.dto;

import com.somefood.board.domain.board.Board;
import com.somefood.board.domain.comment.Comment;
import lombok.Builder;
import lombok.Data;

@Data
public class CommentDto {

    private String content;

    @Builder
    public CommentDto(String content) {
        this.content = content;
    }

    public Comment toEntity() {
        return Comment.builder()
                .content(getContent())
                .build();
    }
}
