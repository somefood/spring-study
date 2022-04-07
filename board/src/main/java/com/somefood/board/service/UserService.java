package com.somefood.board.service;

import com.somefood.board.domain.user.Account;
import com.somefood.board.domain.user.UserRepository;
import com.somefood.board.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void join(UserDto userDto) {

        if (checkIfUserExist(userDto.getUsername())) {
            throw new IllegalArgumentException("이미 아이디가 존재합니다.");
        }

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Account account = userDto.toEntity();
        userRepository.save(account);
    }

    boolean checkIfUserExist(String username) {
        return userRepository.existsAccountByUsername(username);
    }
}
