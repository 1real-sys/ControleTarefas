package dev.teamwin.controletarefa.Service;


import dev.teamwin.controletarefa.DTO.TarefaDTO;
import dev.teamwin.controletarefa.Domain.StatusTarefa;
import dev.teamwin.controletarefa.Domain.TarefaDomain;
import dev.teamwin.controletarefa.Mapper.TarefaMapper;
import dev.teamwin.controletarefa.Repository.TarefaRepository;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;

    public TarefaService(TarefaRepository tarefaRepository, TarefaMapper tarefaMapper) {
        this.tarefaRepository = tarefaRepository;
        this.tarefaMapper = tarefaMapper;
    }

    public TarefaDTO CadastrarTarefa(TarefaDTO tarefaDTO) {
        TarefaDomain DomainTarefa = tarefaMapper.map(tarefaDTO);
         DomainTarefa = tarefaRepository.save(DomainTarefa);
        return tarefaMapper.map(DomainTarefa);
    }

    public TarefaDTO ListarTarefaPorId(Long id){
        Optional <TarefaDomain> tarefaOptional = tarefaRepository.findById(id);
        return tarefaOptional.map(tarefaMapper::map).orElse(null);
    }

    public List<TarefaDTO> ListarTodasTarefas(){
        List<TarefaDomain> tarefaDomains = tarefaRepository.findAll();
        return tarefaDomains.stream()
                .map(tarefaMapper::map)
                .collect(Collectors.toList());
    }

    public String deletarTarefa(Long id){
        TarefaDTO tarefaDTO = tarefaRepository.findById(id)
                .map(tarefaMapper::map)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        tarefaRepository.deleteById(id);
        return "Tarefa " + tarefaDTO.getTitulo() + " foi deletada com sucesso!";
    }

    public TarefaDTO AtualizarTarefa(Long id, TarefaDTO tarefaAtualizada){
        return tarefaRepository.findById(id).map(tarefaExistente -> {
            BeanWrapper origem = new BeanWrapperImpl(tarefaAtualizada);
            BeanWrapper destino = new BeanWrapperImpl(tarefaExistente);

            Set<String> ignorar = Set.of("id", "dataCriacao", "class");

            Arrays.stream(origem.getPropertyDescriptors())
                    .map(PropertyDescriptor::getName)
                    .filter(nome -> !ignorar.contains(nome))
                    .filter(nome -> origem.getPropertyValue(nome) != null)
                    .forEach(nome -> destino.setPropertyValue(nome, origem.getPropertyValue(nome)));
            TarefaDomain domain = tarefaRepository.save(tarefaExistente);
            return tarefaMapper.map(domain);
        }).orElse(null);
    }

    public TarefaDTO ConcluirTarefa(Long id){
        return tarefaRepository.findById(id).map(tarefa -> {
            tarefa.setStatus(StatusTarefa.CONCLUIDA);
            TarefaDomain domain = tarefaRepository.save(tarefa);
            return tarefaMapper.map(domain);
        }).orElse(null);
    }

    public TarefaDTO IniciarTarefa(Long id){
        return tarefaRepository.findById(id).map(tarefa -> {
            tarefa.setStatus(StatusTarefa.EM_ATENDIMENTO);
            TarefaDomain domain = tarefaRepository.save(tarefa);
            return tarefaMapper.map(domain);

        }).orElse(null);
    }





}
