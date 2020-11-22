package io.emma.inventario.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// singleton clase estatica metodo estatico
public class DataBaseConnectionUtils {

    private static Connection conexion = null;

    public static Connection getConnection () throws SQLException,  ClassNotFoundException{
        if(conexion == null){
            Class.forName("com.mysql.jdbc.Driver");
            conexion= DriverManager.getConnection(
                    //? nombre base de datos, ?usuario, ?contrasena
                    "jdbc:mysql://localhost:3306/inventario","root","1234567");
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
