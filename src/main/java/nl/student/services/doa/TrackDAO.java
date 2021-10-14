package nl.student.services.doa;

import nl.student.data.dao.ITrackDAO;
import nl.student.services.doa.Entity.TrackEntity;

import java.sql.*;
import java.util.ArrayList;

public class TrackDAO implements ITrackDAO {

    Connection connection = null;

    @Override
    public ArrayList<TrackEntity> getAllTracks() {
        ArrayList<TrackEntity> tracks = new ArrayList<>();

        DatabaseGetter DbGet = new DatabaseGetter();
        connection = DbGet.getCon();

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
}
