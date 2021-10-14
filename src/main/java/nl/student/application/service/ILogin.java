package nl.student.application.service;

import nl.student.services.domain.dto.UserDTO;

public interface ILogin {
    public UserDTO Login(UserDTO dto);
}
