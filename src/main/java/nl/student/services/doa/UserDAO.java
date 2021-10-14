package nl.student.services.doa;

import nl.student.data.dao.IUserDAO;
import nl.student.services.doa.Entity.UserEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UserDAO implements IUserDAO {

    Connection connection = null;

    @Override
    public UserEntity getUserByUsername(String Username) {
        UserEntity user = null;
        DatabaseGetter DbGet = new DatabaseGetter();
        connection = DbGet.getCon();

        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM [user] where username = '" + Username + "'");
            while(rs.next()){
                user = new UserEntity(
                        rs.getInt("OwnerID"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("token"),
                        rs.getDate("tokenCreateTime")
                );
            }
            //without close endpoint doesn't work
            rs.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public void setNewToken(String Username, UUID token) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(date);

        String stmtToken = String.format("update [user] set token = '%1$s' , tokenCreateTime = '%2$s' where username = '%3$s'",
                token,
                strDate,
                Username
        );

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
        }
    }

    @Override
    public boolean checkIfValidToken(UUID token) {

        String stmtToken = String.format("select token from [user] where token = '%1$s'",token);
        UUID NewToken = null;

        DatabaseGetter DbGet = new DatabaseGetter();
        connection = DbGet.getCon();
        Statement stmt = null;

        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(stmtToken);
            while (rs.next()){
                NewToken = UUID.fromString(rs.getString(1));
            }

            if (NewToken != null){
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            //without close endpoint doesn't work
            try {
                stmt.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
