package nl.student.data.dao;

import nl.student.services.doa.entity.TrackEntity;

import java.util.List;

public interface ITrackDAO {
    List<TrackEntity> getAllTracks();
}
