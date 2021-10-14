package nl.student.data.dao;

import nl.student.services.doa.Entity.TrackEntity;

import java.util.ArrayList;

public interface ITrackDAO {
    public ArrayList<TrackEntity> getAllTracks();
}
