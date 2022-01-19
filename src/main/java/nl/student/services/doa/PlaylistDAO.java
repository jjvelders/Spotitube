package nl.student.services.doa;

import nl.student.data.dao.IPlaylistDAO;
import nl.student.services.doa.entity.PlaylistEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;

public class PlaylistDAO implements IPlaylistDAO {

    Connection connection = null;

    @Override
    public ArrayList<PlaylistEntity> getAll() {
        ArrayList<PlaylistEntity> playlists = new ArrayList<>();

        DatabaseGetter dbGet = new DatabaseGetter();
        connection = dbGet.getCon();

        try (Statement stmt = connection.createStatement()) {
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

        DatabaseGetter databaseGetter = new DatabaseGetter();
        connection = databaseGetter.getCon();

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(stmtToken);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public void editPlaylist(int playlistId, String playlistName) {
        String stmtToken = MessageFormat.format("update [playlist] set name = ''{1}'' where playlistId = {0}", playlistId, playlistName);

        DatabaseGetter databaseGetter = new DatabaseGetter();
        connection = databaseGetter.getCon();

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(stmtToken);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PlaylistEntity getById(int id) {
        PlaylistEntity playlist = null;

        DatabaseGetter dbGet = new DatabaseGetter();
        connection = dbGet.getCon();

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(MessageFormat.format("SELECT * FROM dbo.playlist p where p.playlistId =  {0}", id));
            while(rs.next()){
                playlist = new PlaylistEntity(
                        rs.getInt("playlistId"),
                        rs.getString("name"),
                        rs.getInt("ownerId")
                );
            }
            rs.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playlist;
    }
}
