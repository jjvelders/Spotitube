package nl.student.service.dao;

import nl.student.services.doa.mssql.PlaylistDAO;
import nl.student.services.doa.entity.PlaylistEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class PlaylistDAOTest {

    private PlaylistDAO sut;

    @BeforeEach
    void setUp(){
        //setup objects
        sut = new PlaylistDAO();
    }

    @AfterEach
    void cleanUp(){
        DatabaseCleaner.cleanSqlDb();
    }

    @Test
    public void getById(){
        //ARRANGE
        PlaylistEntity playlistEntity;
        //ACT
        playlistEntity = sut.getById(2);
        //ASSERT
        assertEquals(2,playlistEntity.getId());
    }

    @Test
    public void getByWrongId(){
        //ARRANGE
        PlaylistEntity playlistEntity;
        //ACT
        playlistEntity = sut.getById(1);
        //ASSERT
        assertNull(playlistEntity);
    }

    @Test
    public void getAllPlaylists(){
        //ARRANGE
        List<PlaylistEntity> playlistEntityList;
        //ACT
        playlistEntityList = sut.getAll();
        //ASSERT
        assertEquals(1,playlistEntityList.size());
    }

    @Test
    public void deleteById(){
        //ARRANGE
        PlaylistEntity playlistEntity;
        //ACT
        sut.deleteById(2);
        playlistEntity = sut.getById(2);
        //ASSERT
        assertNull(playlistEntity);
    }

    @Test
    public void addPlaylist(){
        //ARRANGE
        List<PlaylistEntity> playlistEntityList;

        //ACT
        sut.addPlaylist("playlist2",1);
        playlistEntityList = sut.getAll();

        //ASSERT
        assertTrue(playlistEntityList
                .stream()
                .anyMatch(p -> Objects.equals(p.getName(), "playlist2")));
    }

    @Test
    public void editPlaylist(){
        //ARRANGE
        List<PlaylistEntity> playlistEntityList;

        //ACT
        sut.editPlaylist(2,"newPlay");
        playlistEntityList = sut.getAll();

        //ASSERT
        assertTrue(playlistEntityList
                .stream()
                .anyMatch(p -> Objects.equals(p.getName(), "newPlay")));
    }
}
