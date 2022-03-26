package com.somefood.boardproject.domain.board;

import com.somefood.boardproject.domain.BaseEntityTime;
import com.somefood.boardproject.domain.category.Category;
import com.somefood.boardproject.domain.account.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Board extends BaseEntityTime {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Builder
    public Board(String title, String content, Category category, Account account) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.account = account;
    }
}
