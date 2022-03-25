package controlador;

import java.sql.*;

public class Conexion {

    public static String url = "jdbc:mysql://localhost:3306/peaje?useSSL=false&useTimeZone=true&serverTimeZone=UTC&allowPublicKeyRetrieval=true";
    public static String user = "xavier";
    public static String contrasena = "123456a.";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, user, contrasena);
    }

    public static void cerrarResultSet(ResultSet result) throws SQLException {
        result.close();
    }

    public static void cerrarPreparedStatement(PreparedStatement statement) throws SQLException {
        statement.close();
    }

    public static void cerrarConnection(Connection conexion) throws SQLException {
        conexion.close();
    }

}
