package nl.student.service.dao;

import nl.student.services.doa.mssql.DatabaseGetter;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseCleaner {

    public static void cleanSqlDb(){
        Connection connection = DatabaseGetter.getCon();

        try (Statement stmt = connection.createStatement()){
            stmt.execute("EXEC [dbo].[SPcleanDb]");
            DatabaseGetter.giveBack(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
