package com.somefood.boardproject.web.dto;

import com.somefood.boardproject.domain.board.Board;
import com.somefood.boardproject.domain.category.Category;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BoardAddDto {

    private String title;
    private String content;
    private Category category;

    public Board toEntity() {
        return Board.builder()
                .title(getTitle())
                .content(getContent())
                .content(getContent())
                .build();
    }

    @Builder
    public BoardAddDto(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }
}
