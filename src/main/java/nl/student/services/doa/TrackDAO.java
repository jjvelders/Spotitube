package nl.student.services.doa;

import nl.student.data.dao.ITrackDAO;
import nl.student.services.doa.entity.TrackEntity;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TrackDAO implements ITrackDAO {

    @Inject
    private DatabaseGetter databaseGetter;

    Connection connection = null;

    @Override
    public ArrayList<TrackEntity> getAvailableTracksByPlaylistId(int playlistId) {
        ArrayList<TrackEntity> tracks = new ArrayList<>();
        connection = databaseGetter.getCon();

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("select * from track t where t.trackId in (select trackId from playlistTrack pt where pt.playlistId !=" + playlistId + " )" );
            while(rs.next()){
                tracks.add(new TrackEntity(
                        rs.getInt("trackId"),
                        rs.getString("title"),
                        rs.getString("performer"),
                        rs.getInt("duration"),
                        rs.getString("album"),
                        rs.getInt("playcount"),
                        rs.getDate("publicationDate"),
                        rs.getString("description"),
                        rs.getBoolean("offlineAvailable")
                ));
            }
            rs.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tracks;
    }

    @Override
    public List<TrackEntity> getTracksByPlaylistId(int playlistId) {
        ArrayList<TrackEntity> tracks = new ArrayList<>();
        connection = databaseGetter.getCon();

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("select * from track t where t.trackId in (select trackId from playlistTrack pt where pt.playlistId =" + playlistId + " )" );
            while(rs.next()){
                tracks.add(new TrackEntity(
                        rs.getInt("trackId"),
                        rs.getString("title"),
                        rs.getString("performer"),
                        rs.getInt("duration"),
                        rs.getString("album"),
                        rs.getInt("playcount"),
                        rs.getDate("publicationDate"),
                        rs.getString("description"),
                        rs.getBoolean("offlineAvailable")
                ));
            }
            rs.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tracks;
    }

    @Override
    public TrackEntity getById(int trackId) {
        TrackEntity track = null;
        connection = databaseGetter.getCon();

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery( MessageFormat.format("select * from track t where t.trackId = {0}", trackId ));
            while(rs.next()){
                track = new TrackEntity(
                        rs.getInt("trackId"),
                        rs.getString("title"),
                        rs.getString("performer"),
                        rs.getInt("duration"),
                        rs.getString("album"),
                        rs.getInt("playcount"),
                        rs.getDate("publicationDate"),
                        rs.getString("description"),
                        rs.getBoolean("offlineAvailable")
                );
            }
            rs.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return track;
    }

    @Override
    public void addTrackToPlaylist(int trackId, int playlistId) {
        connection = databaseGetter.getCon();

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(MessageFormat.format("INSERT INTO playlistTrack (trackId, playlistId) VALUES({0}, {1})", trackId, playlistId));
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTrackFromPlaylist(int trackId, int playlistId) {
        connection = databaseGetter.getCon();

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(MessageFormat.format("DELETE FROM playlistTrack WHERE trackId = {0} AND playlistId = {1}", trackId, playlistId));
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editTrackAvailability(int trackId, int offlineAvailable) {
        connection = databaseGetter.getCon();

        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(MessageFormat.format("UPDATE track SET offlineAvailable = {1} WHERE trackId = {0}", trackId, offlineAvailable));
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
