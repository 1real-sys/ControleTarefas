package dev.teamwin.controletarefa.Repository;

import dev.teamwin.controletarefa.Domain.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDomain, Long> {
    Optional<UserDomain> findByEmail(String email);
    Optional<UserDomain> findByUsername(String username);
    boolean existsByEmail(String email);
}