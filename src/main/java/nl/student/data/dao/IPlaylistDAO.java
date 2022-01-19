package nl.student.data.dao;

import nl.student.services.doa.entity.PlaylistEntity;

import java.util.List;

public interface IPlaylistDAO {
    List<PlaylistEntity> getAll();
    boolean deleteById(int id);
    void editPlaylist(int playlistId, String playlistName);
    PlaylistEntity getById(int id);
    void addPlaylist(String name, int userId);
}
