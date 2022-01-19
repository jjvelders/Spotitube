package nl.student.services;

import nl.student.application.service.ITracks;
import nl.student.data.dao.ITrackDAO;
import nl.student.services.doa.entity.TrackEntity;
import nl.student.services.domain.dto.TrackDTO;
import nl.student.services.domain.dto.TracksDTO;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class TrackService implements ITracks {

    @Inject
    private ITrackDAO trackDAO;

    @Override
    public TracksDTO getAllTracks() {
        TracksDTO dto = new TracksDTO();
        List<TrackDTO> trackDTOList = new ArrayList<>();

        List<TrackEntity> trackEntityList = trackDAO.getAllTracks();
        for (TrackEntity track: trackEntityList) {
            trackDTOList.add(
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
        dto.setTracks(trackDTOList);
        return dto;
    }

    @Override
    public TracksDTO getTracksByPlaylistId(int playlistId) {
        TracksDTO dto = new TracksDTO();
        List<TrackDTO> trackDTOList = new ArrayList<>();

        List<TrackEntity> trackEntityList = trackDAO.getTracksByPlaylistId(playlistId);
        for (TrackEntity track: trackEntityList) {
            trackDTOList.add(
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
        dto.setTracks(trackDTOList);
        return dto;
    }
}
