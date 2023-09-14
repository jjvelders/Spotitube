package nl.student.services.doa.mssql;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseGetter {

    static Connection connection;

    public static synchronized Connection getCon(){
            try {
                if (connection == null || connection.isClosed()) {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    connection = DriverManager.getConnection(getConnectionString(ConnectionType.LOCALCONNECTIONSTRING));
                    System.out.println("New Connection established");
                }
            } catch (SQLException | IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        return connection;
    }

    public static void giveBack(Connection conn){
        connection = conn;
    }

    private static String getConnectionString(ConnectionType connectionType) throws IOException {
        try {
            Properties prop = new Properties();
            String propFileName = "database.properties";

            InputStream inputStream = DatabaseGetter.class.getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            return prop.getProperty(String.valueOf(connectionType));
        }
        catch (IOException e) {
            throw new IOException(e);
        }
    }

    enum ConnectionType{
        LOCALCONNECTIONSTRING,
        REMOTECONNECTIONSTRING
    }

}
