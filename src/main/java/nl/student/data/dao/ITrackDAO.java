package nl.student.data.dao;

import nl.student.services.doa.entity.TrackEntity;

import java.util.List;

public interface ITrackDAO {
    List<TrackEntity> getAvailableTracksByPlaylistId(int playlistId);
    List<TrackEntity> getTracksByPlaylistId(int playlistId);
    void addTrackToPlaylist(int trackId, int playlistId);
    void deleteTrackFromPlaylist(int trackId, int playlistId);
}
