package dev.teamwin.controletarefa.Service;

import dev.teamwin.controletarefa.DTO.TarefaDTO;
import dev.teamwin.controletarefa.DTO.UserDTO;
import dev.teamwin.controletarefa.Domain.UserDomain;
import dev.teamwin.controletarefa.Mapper.TarefaMapper;
import dev.teamwin.controletarefa.Mapper.UserMapper;
import dev.teamwin.controletarefa.Repository.TarefaRepository;
import dev.teamwin.controletarefa.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper,
                       TarefaRepository tarefaRepository, TarefaMapper tarefaMapper,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.tarefaRepository = tarefaRepository;
        this.tarefaMapper = tarefaMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO cadastrarUsuario(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Já existe um usuário com o e-mail: " + userDTO.getEmail());
        }
        UserDomain domain = userMapper.map(userDTO);
        domain.setPasswordHash(passwordEncoder.encode(userDTO.getPassword()));
        domain = userRepository.save(domain);
        return userMapper.map(domain);
    }

    public UserDTO listarUsuarioPorId(Long id) {
        return userRepository.findById(id)
                .map(userMapper::map)
                .orElse(null);
    }

    public List<UserDTO> listarTodosUsuarios() {
        return userRepository.findAll().stream()
                .map(userMapper::map)
                .collect(Collectors.toList());
    }

    public UserDTO atualizarUsuario(Long id, UserDTO userAtualizado) {
        return userRepository.findById(id).map(usuarioExistente -> {
            if (userAtualizado.getUsername() != null) {
                usuarioExistente.setUsername(userAtualizado.getUsername());
            }
            if (userAtualizado.getEmail() != null) {
                usuarioExistente.setEmail(userAtualizado.getEmail());
            }
            if (userAtualizado.getPassword() != null) {
                usuarioExistente.setPasswordHash(passwordEncoder.encode(userAtualizado.getPassword()));
            }
            if (userAtualizado.getRole() != null) {
                usuarioExistente.setRole(userAtualizado.getRole());
            }
            UserDomain domain = userRepository.save(usuarioExistente);
            return userMapper.map(domain);
        }).orElse(null);
    }

    public String deletarUsuario(Long id) {
        UserDomain usuario = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));
        userRepository.deleteById(id);
        return "Usuário " + usuario.getUsername() + " foi deletado com sucesso!";
    }

    public List<TarefaDTO> listarTarefasDoUsuario(Long usuarioId) {
        if (!userRepository.existsById(usuarioId)) {
            throw new RuntimeException("Usuário não encontrado com id: " + usuarioId);
        }
        return tarefaRepository.findByUsuarioId(usuarioId).stream()
                .map(tarefaMapper::map)
                .collect(Collectors.toList());
    }
}
