package com.marcos.spelltrade.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.marcos.spelltrade.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
