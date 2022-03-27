package com.somefood.board.domain.board;

import com.somefood.board.domain.BaseTimeEntity;
import com.somefood.board.domain.category.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "boards")
@Entity
public class Board extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public void setCategory(Category category) {
        if (this.category != null) { // 기존에 이미 팀이 존재한다면
            this.category.getBoards().remove(this); // 관계를 끊는다.
        }
        this.category = category;
        category.getBoards().add(this);
    }

    @Builder
    public Board(String title, String content, Category category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}


