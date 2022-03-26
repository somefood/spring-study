package com.somefood.boardproject.domain.account;

import com.somefood.boardproject.domain.board.Board;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;
    private String email;
    private int age;
    private String role;

    @OneToMany(mappedBy = "account")
    List<Board> boards = new ArrayList<>();
}

