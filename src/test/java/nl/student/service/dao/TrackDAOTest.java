package nl.student.service.dao;

import nl.student.services.doa.mssql.TrackDAO;

import nl.student.services.doa.entity.TrackEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TrackDAOTest {

    private TrackDAO sut;

    @BeforeEach
    void setUp(){
        //setup objects
        sut = new TrackDAO();
    }

    @AfterEach
    void cleanUp(){
        DatabaseCleaner.cleanSqlDb();
    }

    @Test
    public void getTrackByIdSucces(){
        //ARRANGE
        TrackEntity trackEntity;
        //ACT
        trackEntity = sut.getById(1);
        //ASSERT
        assertEquals(1,trackEntity.getId());
    }

    @Test
    public void getTrackByIdWrongId(){
        //ARRANGE
        TrackEntity trackEntity;
        //ACT
        trackEntity = sut.getById(100);
        //ASSERT
        assertNull(trackEntity);
    }

    @Test
    public void addTrackToPlaylistSucces(){
        //ARRANGE
        List<TrackEntity> trackList;

        //ACT
        sut.addTrackToPlaylist(3, 2);
        trackList = sut.getTracksByPlaylistId(2);

        //ASSERT
        assertTrue(trackList
                .stream()
                .anyMatch(t -> t.getId() == 3)
        );
    }

    @Test
    public void deleteTrackFromPlaylist(){
        //ARRANGE
        List<TrackEntity> trackList;

        //ACT
        sut.deleteTrackFromPlaylist(2, 2);
        trackList = sut.getTracksByPlaylistId(2);

        //ASSERT
        assertTrue(trackList
                .stream()
                .filter(t -> t.getId() == 2)
                .findFirst()
                .isEmpty()
        );
    }

    @Test
    public void editTrackAvailability(){
        //ARRANGE
        TrackEntity trackEntity;

        //ACT
        sut.editTrackAvailability(1,0);
        trackEntity = sut.getById(1);

        //ASSERT
        assertFalse(trackEntity.isOfflineAvailable());
    }

    @Test
    public void getTracksByPlaylistId(){
        //ARRANGE

        //ACT
        List<TrackEntity> tracks = sut.getTracksByPlaylistId(2);

        //ASSERT
        assertEquals(2, tracks.size());
        assertEquals(1, tracks.get(0).getId());
        assertEquals(2, tracks.get(1).getId());
    }

    @Test
    public void getAvailableTracksByPlaylistId(){
        //ARRANGE

        //ACT
        List<TrackEntity> tracks = sut.getAvailableTracksByPlaylistId(2);

        //ASSERT
        assertEquals(1, tracks.size());
        assertEquals(3, tracks.get(0).getId());
    }
}
