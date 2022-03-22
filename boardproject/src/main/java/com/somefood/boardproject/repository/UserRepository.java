package com.somefood.boardproject.repository;

import com.somefood.boardproject.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<Account, Long> {

    Account findByUsername(String username);
}
