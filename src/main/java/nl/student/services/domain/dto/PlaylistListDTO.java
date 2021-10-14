package nl.student.services.domain.dto;

import java.util.ArrayList;

public class PlaylistListDTO {
    private ArrayList<PlaylistDTO> playlists;
    private int length;

    public PlaylistListDTO(ArrayList<PlaylistDTO> playlists, int length) {
        this.playlists = playlists;
        this.length = length;
    }

    public ArrayList<PlaylistDTO> getPlaylists() {
        return playlists;
    }

    public int getLength() {
        return length;
    }
}
