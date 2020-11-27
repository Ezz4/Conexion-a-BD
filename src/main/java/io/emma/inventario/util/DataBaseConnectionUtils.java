package io.emma.inventario.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// singleton clase estatica metodo estatico
public class DataBaseConnectionUtils {

    private static Connection conexion = null;
    private static final String URLCONEXION = new StringBuilder().append("jdbc:mysql://localhost:3306/inventario?")
            .append("useUnicode=true").append("&useJDBCCompliantTimezoneShift=true")
            .append("&useLegacyDatetimeCode=false").append("&serverTimezone=UTC").toString();
    private static  final String USER = "root";
    private static  final String PASWORD = "1234567";
    public static Connection getConnection () throws SQLException,  ClassNotFoundException{
        if(conexion == null){
           // Class.forName("com.mysql.jdbc.Driver"); No necesario para java 7 en adelante solo jdbc
            conexion= DriverManager.getConnection(
                    //? nombre base de datos, ?usuario, ?contrasena
                    URLCONEXION,USER,PASWORD);
        }
        return conexion;
    }
    public static void closeConnection(){

        try {
            conexion.close();
        }catch (SQLException e){
            System.out.println("Conexion no cerrada");
        }
    }
}
