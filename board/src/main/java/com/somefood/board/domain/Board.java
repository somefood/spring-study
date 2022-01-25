package com.somefood.board.domain;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
public class Board {
    private long id;
    private String title;
    private String content;

    public Board() {
    }

    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
