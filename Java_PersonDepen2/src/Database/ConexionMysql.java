package Database;

import java.sql.Connection;
import java.sql.SQLException;
import com.mysql.cj.jdbc.*;

public class ConexionMysql {
	
	private static Connection conn = null;

    // Nombre de la base de datos
    public static String database = "mydb";

    // Host
    public static String hostname = "localhost";

    // Puerto
    public static int port = 3306;
    

    // Nombre de usuario
    public static String username = "admin";
 
    // Clave de usuario
    public static String password = "admin31416.";

    public static Connection getConexion() {
    	MysqlDataSource data = new MysqlDataSource();
    	   data.setUser(username);
    	   data.setPassword(password);
    	   data.setServerName(hostname);
    	   data.setPortNumber(port);
    	   data.setDatabaseName(database);
     
    	if (conn == null) {

        try {
            conn = data.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	}

        return conn;
    }
}