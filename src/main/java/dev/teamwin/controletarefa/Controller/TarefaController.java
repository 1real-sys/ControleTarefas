package dev.teamwin.controletarefa.Controller;


import dev.teamwin.controletarefa.Domain.TarefaDomain;
import dev.teamwin.controletarefa.Service.TarefaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping("/boasVindas")
    public String boasVindas() {
        return "Bem-vindo ao Controle de Tarefas!";
    }

    @PostMapping("/cadastrarTarefa")
    public TarefaDomain cadastrarTarefa(@RequestBody TarefaDomain tarefa) {
        return tarefaService.CadastrarTarefa(tarefa);
    }

    @GetMapping("/listar")
    public List<TarefaDomain> listarTarefas() {
        return tarefaService.ListarTodasTarefas();
   }

   @GetMapping("/listar/{id}")
    public TarefaDomain listarTarefaPorId(@PathVariable Long id) {
       return tarefaService.ListarTarefaPorId(id);
   }

   @PutMapping("/atualizar/{id}")
    public TarefaDomain atualizarTarefa(@PathVariable Long id, @RequestBody TarefaDomain tarefaAtualizada) {
       return tarefaService.AtualizarTarefa(id, tarefaAtualizada);
   }
   @PutMapping("/iniciar/{id}")
   public TarefaDomain iniciarTarefa(@PathVariable Long id) {
        return tarefaService.IniciarTarefa(id);
   }
    @PutMapping("/concluir/{id}")
    public TarefaDomain concluirTarefa(@PathVariable Long id) {
        return tarefaService.ConcluirTarefa(id);
    }


    @DeleteMapping("/deletar/{id}")
    public void deletarTarefa(@PathVariable Long id) {
       tarefaService.deletarTarefa(id);
   }





    }
