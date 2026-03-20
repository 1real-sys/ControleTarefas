package dev.teamwin.controletarefa.Controller;

import dev.teamwin.controletarefa.DTO.TarefaDTO;
import dev.teamwin.controletarefa.DTO.UserDTO;
import dev.teamwin.controletarefa.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/sucesso")
    public ResponseEntity<String> getUser(){
        return ResponseEntity.ok("success");
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UserDTO> cadastrarUsuario(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.cadastrarUsuario(userDTO));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<UserDTO>> listarTodosUsuarios() {
        return ResponseEntity.ok(userService.listarTodosUsuarios());
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<UserDTO> listarUsuarioPorId(@PathVariable Long id) {
        UserDTO usuario = userService.listarUsuarioPorId(id);
        if (usuario == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<UserDTO> atualizarUsuario(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        UserDTO atualizado = userService.atualizarUsuario(id, userDTO);
        if (atualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deletarUsuario(id));
    }

    @GetMapping("/{id}/tarefas")
    public ResponseEntity<List<TarefaDTO>> listarTarefasDoUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(userService.listarTarefasDoUsuario(id));
    }
}
