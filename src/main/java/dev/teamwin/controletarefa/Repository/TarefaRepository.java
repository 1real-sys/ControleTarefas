package dev.teamwin.controletarefa.Repository;

import dev.teamwin.controletarefa.Domain.TarefaDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<TarefaDomain, Long> {
    List<TarefaDomain> findByUsuarioId(Long usuarioId);

}
