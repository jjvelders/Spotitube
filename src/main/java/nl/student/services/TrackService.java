package nl.student.services;

import nl.student.application.service.ITracks;
import nl.student.data.dao.ITrackDAO;
import nl.student.services.doa.Entity.TrackEnitity;
import nl.student.services.domain.dto.TrackDTO;
import nl.student.services.domain.dto.TracksDTO;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class TrackService implements ITracks {

    @Inject
    private ITrackDAO DAO;

    @Override
    public TracksDTO getAllTracks() {
        TracksDTO dto = new TracksDTO();

        ArrayList<TrackEnitity> trackEnitityList = DAO.getAllTracks();
        ArrayList<TrackDTO> TrackDTOList = new ArrayList<>();

        for (TrackEnitity track: trackEnitityList) {
            TrackDTOList.add(
                    new TrackDTO(
                            track.getId(),
                            track.getTitle(),
                            track.getPerformer(),
                            track.getDuration(),
                            track.getAlbum(),
                            track.getPlaycount(),
                            track.getPublicationDate(),
                            track.getDescription(),
                            track.getIsOfflineAvailable()
                    )
            );
        }

        dto.setTracks(TrackDTOList);

        return dto;
    }
}