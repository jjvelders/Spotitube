package nl.student.application.service;

import nl.student.services.domain.dto.TracksDTO;

public interface ITracks {
    TracksDTO getAllTracks();

    TracksDTO getTracksByPlaylistId(int playlistId);
}
