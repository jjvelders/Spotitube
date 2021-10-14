package nl.student.data.dao;

import nl.student.services.doa.Entity.PlaylistEntity;

import java.util.ArrayList;

public interface IPlaylistDAO {
    public ArrayList<PlaylistEntity> getAll();
    public boolean deleteById(int id);

    boolean deleteTrack(int id);
}