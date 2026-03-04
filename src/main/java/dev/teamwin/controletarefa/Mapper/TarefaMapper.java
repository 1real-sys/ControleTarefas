package dev.teamwin.controletarefa.Mapper;

import dev.teamwin.controletarefa.DTO.TarefaDTO;
import dev.teamwin.controletarefa.Domain.TarefaDomain;
import org.springframework.stereotype.Component;

@Component
public class TarefaMapper {

    public TarefaDomain map(TarefaDTO dtoTarefa) {
        TarefaDomain tarefaDomain = new TarefaDomain();
        tarefaDomain.setId(dtoTarefa.getId());
        tarefaDomain.setTitulo(dtoTarefa.getTitulo());
        tarefaDomain.setDescricao(dtoTarefa.getDescricao());
        tarefaDomain.setDataCriacao(dtoTarefa.getDataCriacao());
        tarefaDomain.setStatus(dtoTarefa.getStatus());
        return tarefaDomain;

    }

    public TarefaDTO map(TarefaDomain domainTarefa) {
        TarefaDTO tarefaDTO = new TarefaDTO();
        tarefaDTO.setId(domainTarefa.getId());
        tarefaDTO.setTitulo(domainTarefa.getTitulo());
        tarefaDTO.setDescricao(domainTarefa.getDescricao());
        tarefaDTO.setDataCriacao(domainTarefa.getDataCriacao());
        tarefaDTO.setStatus(domainTarefa.getStatus());
        return tarefaDTO;
    }
}
