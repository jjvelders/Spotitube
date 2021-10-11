package nl.student.services.doa;

import nl.student.data.dao.ITrackDAO;
import nl.student.services.doa.Entity.TrackEnitity;

import java.sql.*;
import java.util.ArrayList;

public class TrackDAO implements ITrackDAO {

    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    @Override
    public ArrayList<TrackEnitity> getAllTracks() {
        ArrayList<TrackEnitity> tracks = new ArrayList<>();

        DatabaseGetter DbGet = new DatabaseGetter();
        connection = DbGet.getCon();

        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM track");
            while(rs.next()){
                tracks.add(new TrackEnitity(
                        rs.getInt("id"),
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tracks;
    }
}
