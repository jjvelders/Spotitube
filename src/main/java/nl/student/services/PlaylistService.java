package nl.student.services;

import nl.student.application.service.IPlaylist;
import nl.student.data.dao.IPlaylistDAO;
import nl.student.services.doa.entity.PlaylistEntity;
import nl.student.services.domain.dto.PlaylistDTO;
import nl.student.services.domain.dto.PlaylistListDTO;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class PlaylistService implements IPlaylist{

    @Inject
    private IPlaylistDAO playlistDAO;

    @Override
    public PlaylistListDTO getAllPlaylists() {
        List<PlaylistDTO> playlists = new ArrayList<>();
        List<PlaylistEntity> playlistEntities = new ArrayList<>(playlistDAO.getAll());

        for (PlaylistEntity entity: playlistEntities) {
            boolean ownerBool = true;
            if (entity.getOwnerId() == 0){
                ownerBool = false;
            }
            playlists.add(new PlaylistDTO(
                    entity.getId(),
                    entity.getName(),
                    ownerBool
            ));
        }

        return new PlaylistListDTO(playlists, 0);
    }

    @Override
    public PlaylistListDTO editPlaylist(int playlistId,PlaylistDTO playlistDTO) {
        PlaylistEntity playlistEntity = getById(playlistId);

        if (playlistEntity.getId() == playlistId || playlistDTO != null) {
            if (playlistEntity.getName() != playlistDTO.getName()){
                playlistDAO.editPlaylist(playlistId, playlistDTO.getName());
            }
        }
        else {
            System.out.println("No such playlist found!");
        }
        return getAllPlaylists();
    }

    @Override
    public boolean deleteTrack(int id) {
        return playlistDAO.deleteById(id);
    }

    private PlaylistEntity getById(int id){
        return playlistDAO.getById(id);
    }

}
