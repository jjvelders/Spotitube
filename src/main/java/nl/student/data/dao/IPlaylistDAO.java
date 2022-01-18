package nl.student.data.dao;

import nl.student.services.doa.entity.PlaylistEntity;

import java.util.List;

public interface IPlaylistDAO {
    List<PlaylistEntity> getAll();
    boolean deleteById(int id);
}
