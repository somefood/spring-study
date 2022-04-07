package com.somefood.board.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Account, Long> {

    public Account findByUsername(String username);

    public boolean existsAccountByUsername(String username);
}
