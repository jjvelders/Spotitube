package nl.student.services;

import nl.student.application.service.IPlaylist;
import nl.student.data.dao.IPlaylistDAO;
import nl.student.services.doa.Entity.PlaylistEntity;
import nl.student.services.domain.dto.PlaylistDTO;
import nl.student.services.domain.dto.PlaylistListDTO;

import javax.inject.Inject;
import java.util.ArrayList;

public class PlaylistService implements IPlaylist{

    @Inject
    private IPlaylistDAO DAO;

    @Override
    public PlaylistListDTO getAllPlaylists() {
        ArrayList<PlaylistEntity> DbList = new ArrayList<>(DAO.getAll());

        ArrayList<PlaylistDTO> playlists = new ArrayList<>();

        for (PlaylistEntity entity: DbList) {
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
    public boolean deleteTrack(int id) {
        return DAO.deleteById(id);
    }
}
