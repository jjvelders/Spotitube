package nl.student.services.doa;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseGetter {

    public Connection getCon(){
        Connection conn = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(this.getConnectionString(ConnectionType.REMOTECONNECTIONSTRING));
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    private String getConnectionString(ConnectionType connectionType) throws IOException {
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
