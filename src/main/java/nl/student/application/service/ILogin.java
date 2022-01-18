package nl.student.application.service;

import nl.student.services.domain.dto.UserDTO;

import java.util.UUID;

public interface ILogin {
    boolean validToken(UUID token);
    UserDTO login(UserDTO dto);
}
