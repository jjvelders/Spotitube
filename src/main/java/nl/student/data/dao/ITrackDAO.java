package nl.student.data.dao;

import nl.student.services.doa.entity.TrackEntity;

import java.util.List;

public interface ITrackDAO {
    List<TrackEntity> getAvailableTracksByPlaylistId(int playlistId);
    List<TrackEntity> getTracksByPlaylistId(int playlistId);
    TrackEntity getById(int trackId);
    void addTrackToPlaylist(int trackId, int playlistId);
    void editTrackAvailability(int id, int offlineAvailable);
    void deleteTrackFromPlaylist(int trackId, int playlistId);

}
