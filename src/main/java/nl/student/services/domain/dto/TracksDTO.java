package nl.student.services.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class TracksDTO {

    List<TrackDTO> tracks = new ArrayList<>();

    public List<TrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }
}