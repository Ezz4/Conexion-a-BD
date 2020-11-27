package io.emma.inventario;


import io.emma.inventario.model.Herramienta;
import io.emma.inventario.util.DataBaseConnectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Inventario {
    public static void main(String[] args){

        Connection conexion = null;

        try{
            conexion = DataBaseConnectionUtils.getConnection();
        }catch (ClassNotFoundException exeptionClass){
            System.out.println("{\n" +
                    "  \"statusCode\": 500,\n" +
                    "  \"message\": \"No se pudo cargar el driver\"\n" +
                    "}");
        }catch (SQLException exeptionSql){
            System.out.println("{\n" +
                    "  \"statusCode\": 500,\n" +
                    "  \"message\": \"No se pudo conectar a la based de datos\"\n" +
                    "}");
        }

        try {
            Statement stmt= null;
            stmt = conexion.createStatement();
            ResultSet rs=stmt.executeQuery("select * from herramientas");
            while(rs.next()){
                Herramienta herramienta = new Herramienta(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("cantidad"),
                        rs.getString("categoria"));
                System.out.println(herramienta);
            }

            conexion.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
