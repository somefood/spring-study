package com.somefood.boardproject.domain;

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

    @OneToMany(mappedBy = "category")
    List<Board> boards = new ArrayList<>();

    @Builder
    public Category(CategoryType type) {
        this.type = type;
    }
}
