package com.somefood.boardproject.service.impl;

import com.somefood.boardproject.domain.Account;
import com.somefood.boardproject.repository.UserRepository;
import com.somefood.boardproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void createUser(Account account) {
        userRepository.save(account);
    }
}
