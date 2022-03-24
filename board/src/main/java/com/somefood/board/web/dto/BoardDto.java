package com.somefood.board.web.dto;

import com.somefood.board.domain.board.Board;
import com.somefood.board.domain.category.Category;
import com.somefood.board.domain.category.CategoryType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@Data
public class BoardDto {

    private String title;
    private String content;
    private CategoryType categoryType;

    @Builder
    public BoardDto(String title, String content, CategoryType categoryType) {
        this.title = title;
        this.content = content;
        this.categoryType = categoryType;
    }

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .build();
    }
}
