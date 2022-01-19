package nl.student.application.service;

import nl.student.services.domain.dto.PlaylistDTO;
import nl.student.services.domain.dto.PlaylistListDTO;

import java.util.UUID;

public interface IPlaylistService {
    PlaylistListDTO getAllPlaylists();
    PlaylistListDTO editPlaylist(int playlistId, PlaylistDTO playlistDTO);
    PlaylistListDTO deleteTrack(int id);
    PlaylistListDTO addPlaylist(PlaylistDTO playlistDTO, UUID token);
}
