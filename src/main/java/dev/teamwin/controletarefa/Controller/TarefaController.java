package dev.teamwin.controletarefa.Controller;


import dev.teamwin.controletarefa.DTO.TarefaDTO;
import dev.teamwin.controletarefa.Domain.TarefaDomain;
import dev.teamwin.controletarefa.Service.TarefaService;
import org.springframework.http.ResponseEntity;
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
    public TarefaDTO cadastrarTarefa(@RequestBody TarefaDTO tarefaDto) {
        return tarefaService.CadastrarTarefa(tarefaDto);
    }

    @GetMapping("/listar")
    public List<TarefaDTO> listarTarefas() {
        return tarefaService.ListarTodasTarefas();
   }

   @GetMapping("/listar/{id}")
    public TarefaDTO listarTarefaPorId(@PathVariable Long id) {
       return tarefaService.ListarTarefaPorId(id);
   }

   @PutMapping("/atualizar/{id}")
    public TarefaDTO atualizarTarefa(@PathVariable Long id, @RequestBody TarefaDTO tarefaAtualizada) {
       return tarefaService.AtualizarTarefa(id, tarefaAtualizada);
   }
   @PutMapping("/iniciar/{id}")
   public TarefaDTO iniciarTarefa(@PathVariable Long id) {
        return tarefaService.IniciarTarefa(id);
   }
    @PutMapping("/concluir/{id}")
    public TarefaDTO concluirTarefa(@PathVariable Long id) {
        return tarefaService.ConcluirTarefa(id);
    }


    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarTarefa(@PathVariable Long id) {
       String mensagem = tarefaService.deletarTarefa(id);
       return ResponseEntity.ok(mensagem);
   }





    }
