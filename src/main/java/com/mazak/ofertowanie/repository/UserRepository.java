package com.mazak.ofertowanie.repository;

import com.mazak.ofertowanie.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByLogin(String login);
}
