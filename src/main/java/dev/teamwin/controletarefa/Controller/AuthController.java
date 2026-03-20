package dev.teamwin.controletarefa.Controller;


import dev.teamwin.controletarefa.DTO.LoginRequestDTO;
import dev.teamwin.controletarefa.DTO.RegisterRequestDTO;
import dev.teamwin.controletarefa.DTO.ResponseDTO;
import dev.teamwin.controletarefa.DTO.UserDTO;
import dev.teamwin.controletarefa.Domain.UserDomain;
import dev.teamwin.controletarefa.Repository.UserRepository;
import dev.teamwin.controletarefa.infra.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body) {
        UserDomain user = this.userRepository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(body.password(), user.getPasswordHash() )){
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getUsername(), token));
        }
        return ResponseEntity.badRequest().build();


    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body) {
        Optional<UserDomain> user = this.userRepository.findByEmail(body.email());

        if(user.isEmpty()){
            UserDomain NewUser = new UserDomain();
            NewUser.setPasswordHash(passwordEncoder.encode(body.password()));
            NewUser.setEmail(body.email());
            NewUser.setUsername(body.name());
            this.userRepository.save(NewUser);

            String token = this.tokenService.generateToken(NewUser);
            return ResponseEntity.ok(new ResponseDTO(NewUser.getUsername(), token));
        }

        return ResponseEntity.badRequest().build();



    }
}
