package com.somefood.board.domain.category;

import com.somefood.board.domain.board.Board;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long Id;

    @Enumerated(EnumType.STRING)
    private CategoryType type;

    @OneToMany(mappedBy = "category")
    private List<Board> boards = new ArrayList<>();
}
