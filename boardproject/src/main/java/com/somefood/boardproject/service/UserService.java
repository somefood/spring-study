package com.somefood.boardproject.service;

import com.somefood.boardproject.domain.Account;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

    void createUser(Account account);
}
