package nl.student.data.dao;

import nl.student.services.doa.Entity.UserEntity;

import java.util.UUID;

public interface IUserDAO {
    public UserEntity getUserByUsername(String Username);
    public void setNewToken(String Username, UUID token);
}
