package nl.student.application.service;

import nl.student.services.domain.dto.UserDTO;

import java.util.UUID;

public interface ILoginService {
    boolean validToken(UUID token);
    UserDTO login(UserDTO dto);
    int getIdFromToken(UUID token);
}
