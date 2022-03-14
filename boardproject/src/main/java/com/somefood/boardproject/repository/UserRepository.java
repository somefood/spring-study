package com.somefood.boardproject.repository;

import com.somefood.boardproject.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Account, Long> {
}
