package nl.student.services;

import nl.student.application.service.ITracksService;
import nl.student.data.dao.ITrackDAO;
import nl.student.services.doa.entity.TrackEntity;
import nl.student.services.domain.dto.TrackDTO;
import nl.student.services.domain.dto.TracksDTO;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class TrackService implements ITracksService {

    private final ITrackDAO trackDAO;

    @Inject
    public TrackService(ITrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    @Override
    public TracksDTO getAvailableTracksByPlaylistId(int playlistId) {
        TracksDTO dto = new TracksDTO();
        List<TrackDTO> trackDTOList = new ArrayList<>();

        List<TrackEntity> trackEntityList = trackDAO.getAvailableTracksByPlaylistId(playlistId);
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
                            track.isOfflineAvailable()
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
                            track.isOfflineAvailable()
                    )
            );
        }
        dto.setTracks(trackDTOList);
        return dto;
    }

    @Override
    public TracksDTO addTrackToPlaylist(TrackDTO trackDTO, int playlistId) {
        trackDAO.addTrackToPlaylist(trackDTO.getId(), playlistId);
        editTrack(trackDTO);
        return getTracksByPlaylistId(playlistId);
    }

    @Override
    public TracksDTO deleteTrackFromPlaylist(int playlistId, int trackId) {
        trackDAO.deleteTrackFromPlaylist(trackId, playlistId);
        return getTracksByPlaylistId(playlistId);
    }

    private void editTrack(TrackDTO trackDTO){
        TrackEntity trackEntity = getById(trackDTO.getId());
        if(trackEntity.isOfflineAvailable() != trackDTO.isOfflineAvailable()){
            int bit = trackDTO.isOfflineAvailable() ? 1 : 0;
            trackDAO.editTrackAvailability(trackDTO.getId(), bit);
        }
    }

    private TrackEntity getById(int trackId){
        return trackDAO.getById(trackId);
    }
}
