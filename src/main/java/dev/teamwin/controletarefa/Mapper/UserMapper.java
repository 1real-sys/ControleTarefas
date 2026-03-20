package dev.teamwin.controletarefa.Mapper;

import dev.teamwin.controletarefa.DTO.UserDTO;
import dev.teamwin.controletarefa.Domain.UserDomain;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDomain map(UserDTO userDTO) {
        UserDomain userDomain = new UserDomain();
        userDomain.setId(userDTO.getId());
        userDomain.setUsername(userDTO.getUsername());
        userDomain.setEmail(userDTO.getEmail());
        userDomain.setRole(userDTO.getRole());
        if (userDTO.getPassword() != null) {
            userDomain.setPasswordHash(userDTO.getPassword());
        }
        return userDomain;
    }

    public UserDTO map(UserDomain userDomain) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userDomain.getId());
        userDTO.setUsername(userDomain.getUsername());
        userDTO.setEmail(userDomain.getEmail());
        userDTO.setRole(userDomain.getRole());
        userDTO.setCreatedAt(userDomain.getCreatedAt());
        // password não é exposto na resposta
        return userDTO;
    }
}
