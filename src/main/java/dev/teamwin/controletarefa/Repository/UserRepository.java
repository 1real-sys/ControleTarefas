package dev.teamwin.controletarefa.Repository;

import dev.teamwin.controletarefa.Domain.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDomain, Long> {
}