package dev.teamwin.controletarefa.Service;


import dev.teamwin.controletarefa.Domain.StatusTarefa;
import dev.teamwin.controletarefa.Domain.TarefaDomain;
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

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public TarefaDomain CadastrarTarefa(TarefaDomain tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public TarefaDomain ListarTarefaPorId(Long id){
        Optional <TarefaDomain> tarefaOptional = tarefaRepository.findById(id);
        return tarefaOptional.orElse(null);
    }

    public List<TarefaDomain> ListarTodasTarefas(){
        return tarefaRepository.findAll();

    }

    public void deletarTarefa(Long id){
        tarefaRepository.deleteById(id);
    }

    public TarefaDomain AtualizarTarefa(Long id, TarefaDomain tarefaAtualizada){
        return tarefaRepository.findById(id).map(tarefaExistente -> {
            BeanWrapper origem = new BeanWrapperImpl(tarefaAtualizada);
            BeanWrapper destino = new BeanWrapperImpl(tarefaExistente);

            Set<String> ignorar = Set.of("id", "dataCriacao", "class");

            Arrays.stream(origem.getPropertyDescriptors())
                    .map(PropertyDescriptor::getName)
                    .filter(nome -> !ignorar.contains(nome))
                    .filter(nome -> origem.getPropertyValue(nome) != null)
                    .forEach(nome -> destino.setPropertyValue(nome, origem.getPropertyValue(nome)));

            return tarefaRepository.save(tarefaExistente);
        }).orElse(null);
    }

    public TarefaDomain ConcluirTarefa(Long id){
        return tarefaRepository.findById(id).map(tarefa -> {
            tarefa.setStatus(StatusTarefa.CONCLUIDA);
            return tarefaRepository.save(tarefa);
        }).orElse(null);
    }

    public TarefaDomain IniciarTarefa(Long id){
        return tarefaRepository.findById(id).map(tarefa -> {
            tarefa.setStatus(StatusTarefa.EM_ATENDIMENTO);
            return tarefaRepository.save(tarefa);
        }).orElse(null);
    }





}
