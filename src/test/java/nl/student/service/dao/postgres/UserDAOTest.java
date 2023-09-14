package nl.student.service.dao.postgres;

import nl.student.service.dao.DatabaseCleaner;
import nl.student.services.doa.entity.UserEntity;
import nl.student.services.doa.postgres.UserDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {

    private UserDAO sut;

    @BeforeEach
    void setUp(){
        //setup objects
        sut = new UserDAO();
    }

    @AfterEach
    void cleanUp(){
        DatabaseCleaner.cleanPostgresDb();
    }

    @Test
    public void getByUsername(){
        //ARRANGE
        UserEntity userEntity;
        //ACT
        userEntity = sut.getUserByUsername("jordy");
        //ASSERT
        assertEquals("jordy",userEntity.getUsername());
    }

    @Test
    public void getByWrongUsername(){
        //ARRANGE
        UserEntity userEntity;
        //ACT
        userEntity = sut.getUserByUsername("jordys");
        //ASSERT
        assertNull(userEntity);
    }

    @Test
    public void getByToken(){
        //ARRANGE
        UserEntity userEntity;
        //ACT
        userEntity = sut.getUserByToken(UUID.fromString("67abb76b-115b-4fef-961b-375faef8f946"));
        //ASSERT
        assertEquals("67abb76b-115b-4fef-961b-375faef8f946",userEntity.getToken());
    }

    @Test
    public void getByWrongToken(){
        //ARRANGE
        UserEntity userEntity;
        //ACT
        userEntity = sut.getUserByToken(UUID.fromString("67abb76b-115b-4fef-961b-375faef8f945"));
        //ASSERT
        assertNull(userEntity);
    }

    @Test
    public void setNewToken(){
        //ARRANGE
        UserEntity userEntity;
        //ACT
        sut.setNewToken("jordy", UUID.fromString("67abb76b-115b-4fef-961b-375faef8f946"));
        userEntity = sut.getUserByUsername("jordy");
        //ASSERT
        assertEquals("67abb76b-115b-4fef-961b-375faef8f946",userEntity.getToken());
    }

    @Test
    public void setNewTokenWrongUserName(){
        //ARRANGE
        UserEntity userEntity;
        //ACT
        sut.setNewToken("jordys", UUID.fromString("67abb76b-115b-4fef-961b-375faef8f946"));
        userEntity = sut.getUserByUsername("jordys");
        //ASSERT
        assertNull(userEntity);
    }

    @Test
    public void checkIfTokenExists(){
        //ARRANGE
        boolean bool;
        //ACT
        bool = sut.checkIfValidToken(UUID.fromString("67abb76b-115b-4fef-961b-375faef8f946"));

        //ASSERT
        assertTrue(bool);
    }

    @Test
    public void checkIfFalseTokenExists(){
        //ARRANGE
        boolean bool;
        //ACT
        bool = sut.checkIfValidToken(UUID.fromString("69abb76b-115b-4fef-961b-375faef8f945"));

        //ASSERT
        assertFalse(bool);
    }
}
