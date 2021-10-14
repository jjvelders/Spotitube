package nl.student.application.service;

import nl.student.services.domain.dto.UserDTO;

import java.util.UUID;

public interface ILogin {
    public boolean ValidToken(UUID token);
    public UserDTO Login(UserDTO dto);
}
