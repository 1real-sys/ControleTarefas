package dev.teamwin.controletarefa.DTO;

import dev.teamwin.controletarefa.Domain.RoleUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
    private RoleUser role;
    private LocalDateTime createdAt;
}
