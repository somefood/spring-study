package com.somefood.board.web.dto;

import com.somefood.board.domain.board.Board;
import com.somefood.board.domain.category.Category;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class BoardDto {

    private String title;
    private String content;
    private Category category;

    @Builder
    public BoardDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .build();
    }
}
