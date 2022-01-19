package nl.student.data.dao;

import nl.student.services.doa.entity.UserEntity;

import java.util.UUID;

public interface IUserDAO {
    UserEntity getUserByUsername(String username);
    void setNewToken(String username, UUID token);
    boolean checkIfValidToken(UUID token);

    UserEntity getUserByToken(UUID token);
}
