package nl.student.application.service;

import nl.student.services.domain.dto.TracksDTO;

public interface ITracksService {
    TracksDTO getAvailableTracksByPlaylistId(int playlistId);
    TracksDTO getTracksByPlaylistId(int playlistId);
    TracksDTO addTrackToPlaylist(int trackDTO, int playlistId);
    TracksDTO deleteTrackFromPlaylist(int playlistId, int trackId);
}
