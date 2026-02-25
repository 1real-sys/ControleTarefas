package dev.teamwin.controletarefa.Repository;

import dev.teamwin.controletarefa.Domain.TarefaDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<TarefaDomain, Long> {
}
