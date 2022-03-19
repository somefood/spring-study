package com.somefood.boardproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private BoardType type;

    @OneToMany(mappedBy = "board")
    List<Article> articles = new ArrayList<>();

    @Builder
    public Board(BoardType type) {
        this.type = type;
    }
}
