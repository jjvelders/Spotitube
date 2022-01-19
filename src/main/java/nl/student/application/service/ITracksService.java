package nl.student.application.service;

import nl.student.services.domain.dto.TrackDTO;
import nl.student.services.domain.dto.TracksDTO;

public interface ITracksService {
    TracksDTO getAvailableTracksByPlaylistId(int playlistId);
    TracksDTO getTracksByPlaylistId(int playlistId);
    TracksDTO addTrackToPlaylist(TrackDTO trackDTO, int playlistId);
    TracksDTO deleteTrackFromPlaylist(int playlistId, int trackId);
}
