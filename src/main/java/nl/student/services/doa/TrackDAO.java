package nl.student.services.doa;

import nl.student.data.dao.ITrackDAO;
import nl.student.services.doa.entity.TrackEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TrackDAO implements ITrackDAO {

    Connection connection = null;

    @Override
    public ArrayList<TrackEntity> getAllTracks() {
        ArrayList<TrackEntity> tracks = new ArrayList<>();

        DatabaseGetter databaseGetter = new DatabaseGetter();
        connection = databaseGetter.getCon();

        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM dbo.track");
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

        DatabaseGetter databaseGetter = new DatabaseGetter();
        connection = databaseGetter.getCon();

        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from track t where t.trackId = (select trackId from playlistTrack pt where pt.playlistId =" + playlistId + " )" );
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
}
