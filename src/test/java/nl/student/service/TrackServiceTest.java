package nl.student.service;

import nl.student.data.dao.ITrackDAO;
import nl.student.services.TrackService;
import nl.student.services.doa.entity.TrackEntity;
import nl.student.services.domain.dto.TrackDTO;
import nl.student.services.domain.dto.TracksDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

class TrackServiceTest {

    //variables or services
    private TrackService sut;
    private ITrackDAO mockedTrackDAO;

    @BeforeEach
    void setUp(){
        //setup mocked objects
        mockedTrackDAO = Mockito.mock(ITrackDAO.class);
        sut = new TrackService(mockedTrackDAO);
    }

    @Test
    void getAvailableTracksByPlaylistId(){
        //GIVEN
        TracksDTO tracksDTO;
        List<TrackEntity> trackEntityList = new ArrayList<>();
        int playlistId = 2;

        trackEntityList.add(new TrackEntity(
                1,
                "",
                "",
                0,
                "",
                20,
                null,
                "",
                true
        ));

        //WHEN
        Mockito.when(mockedTrackDAO.getAvailableTracksByPlaylistId(playlistId)).thenReturn(trackEntityList);

        //THEN
        tracksDTO = sut.getAvailableTracksByPlaylistId(playlistId);
        List<TrackDTO> list = tracksDTO.getTracks();
        Assertions.assertEquals(1, list.size());
    }
}
