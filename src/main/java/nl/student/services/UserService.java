package nl.student.services;

import nl.student.application.service.ILoginService;
import nl.student.data.dao.IUserDAO;
import nl.student.services.doa.entity.UserEntity;
import nl.student.services.domain.dto.UserDTO;
import org.apache.commons.codec.digest.DigestUtils;

import javax.inject.Inject;
import java.util.UUID;

public class UserService implements ILoginService {

    @Inject
    private IUserDAO userDAO;

    @Override
    public boolean validToken(UUID token) {
        return userDAO.checkIfValidToken(token);
    }

    @Override
    public UserDTO login(UserDTO dto) {

        UserEntity user = userDAO.getUserByUsername(dto.getUser());


        //if (DigestUtils.sha256Hex(dto.getPassword()).equals(user.getPassword()))
        if (dto.getPassword().equals(user.getPassword()))
        {
            //right user
            //get token + creation date
            UUID token = UUID.randomUUID();
            userDAO.setNewToken(dto.getUser(), token);

            return new UserDTO(token.toString(), user.getFirstName() + " " + user.getLastName());
        }
        else
        {
            return null;
        }
    }

    @Override
    public int getIdFromToken(UUID token) {
        UserEntity user = userDAO.getUserByToken(token);
        return user.getOwnerId();
    }
}
