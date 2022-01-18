package nl.student.services.domain.dto;

import java.util.List;

public class PlaylistListDTO {
    private final List<PlaylistDTO> playlists;
    private final int length;

    public PlaylistListDTO(List<PlaylistDTO> playlists, int length) {
        this.playlists = playlists;
        this.length = length;
    }

    public List<PlaylistDTO> getPlaylists() {
        return playlists;
    }

    public int getLength() {
        return length;
    }
}
