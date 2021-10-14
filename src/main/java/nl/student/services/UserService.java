package nl.student.services;

import nl.student.application.service.ILogin;
import nl.student.data.dao.IUserDAO;
import nl.student.services.doa.Entity.UserEntity;
import nl.student.services.domain.dto.UserDTO;
import org.apache.commons.codec.digest.DigestUtils;

import javax.inject.Inject;
import java.util.UUID;

public class UserService implements ILogin {

    @Inject
    private IUserDAO DAO;

    @Override
    public UserDTO Login(UserDTO dto) {

        UserEntity user = DAO.getUserByUsername(dto.getUser());

        if (DigestUtils.sha256Hex(dto.getPassword()).equals(user.getPassword()))
        {
            //right user
            //get token + creation date
            UUID token = UUID.randomUUID();
            DAO.setNewToken(dto.getUser(), token);

            return new UserDTO(token.toString(), user.getFirstName() + " " + user.getLastName());
        }
        else
        {
            return null;
        }
    }
}
