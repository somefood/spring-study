package com.somefood.board.domain.category;

import com.somefood.board.domain.board.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long Id;

    @Enumerated(EnumType.STRING)
    private CategoryType type;

    private String description;
    private String color;

    @OneToMany(mappedBy = "category")
    private List<Board> boards = new ArrayList<>();

    @Builder
    public Category(CategoryType type, String description, String color) {
        this.type = type;
        this.description = description;
        this.color = color;
    }
}
