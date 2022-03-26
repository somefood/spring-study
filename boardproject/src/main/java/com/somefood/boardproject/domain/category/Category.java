package com.somefood.boardproject.domain.category;

import com.somefood.boardproject.domain.board.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private CategoryType type;

    private String description;

    private String color;

    @OneToMany(mappedBy = "category")
    List<Board> boards = new ArrayList<>();

    @Builder
    public Category(CategoryType type, String description, String color) {
        this.type = type;
        this.description = description;
        this.color = color;
    }
}
