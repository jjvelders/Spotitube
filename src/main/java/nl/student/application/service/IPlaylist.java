package nl.student.application.service;

import nl.student.services.domain.dto.PlaylistDTO;
import nl.student.services.domain.dto.PlaylistListDTO;

public interface IPlaylist {
    PlaylistListDTO getAllPlaylists();
    PlaylistListDTO editPlaylist(int playlistId, PlaylistDTO playlistDTO);
    boolean deleteTrack(int id);
}
