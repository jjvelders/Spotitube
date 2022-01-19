package nl.student.application.service;

import nl.student.services.domain.dto.PlaylistListDTO;

public interface IPlaylist {
    PlaylistListDTO getAllPlaylists();
    PlaylistListDTO editPlaylist();
    boolean deleteTrack(int id);
}
