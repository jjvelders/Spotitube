package nl.student.services.doa;

import nl.student.data.dao.IPlaylistDAO;
import nl.student.services.doa.Entity.PlaylistEntity;
import nl.student.services.doa.Entity.TrackEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PlaylistDAO implements IPlaylistDAO {

    Connection connection = null;

    @Override
    public ArrayList<PlaylistEntity> getAll() {
        ArrayList<PlaylistEntity> playlists = new ArrayList<>();

        DatabaseGetter DbGet = new DatabaseGetter();
        connection = DbGet.getCon();

        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM dbo.playlist");
            while(rs.next()){
                playlists.add(new PlaylistEntity(
                        rs.getInt("playlistId"),
                        rs.getString("name"),
                        rs.getInt("ownerId")
                ));
            }
            rs.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return playlists;
    }

    @Override
    public boolean deleteById(int id) {
        String stmtToken = String.format("delete from [playlist] where playlistId = '%1$s'", id);

        DatabaseGetter DbGet = new DatabaseGetter();
        connection = DbGet.getCon();

        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(stmtToken);

            //without close endpoint doesn't work
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
