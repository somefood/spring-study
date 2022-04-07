package com.somefood.board.web.dto;

import com.somefood.board.domain.user.Account;
import lombok.Data;

@Data
public class UserDto {

    private String username;
    private String password;

    public Account toEntity() {
        return Account.builder()
                .username(getUsername())
                .password(getPassword())
                .role("ROLE_USER")
                .build();
    }
}
