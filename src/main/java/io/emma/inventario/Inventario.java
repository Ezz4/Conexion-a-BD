package io.emma.inventario;


import io.emma.inventario.exception.DaoException;
import io.emma.inventario.model.Herramienta;
import io.emma.inventario.repository.DaoHerramienta;
import io.emma.inventario.util.DataBaseConnectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class Inventario {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int menu = 0;
        int id = 0;

        try{
            Connection conexion = DataBaseConnectionUtils.getConnection();
            System.out.println("Ingresa 1 para leer los registros d ela base de datos");
            System.out.println("Ingresa 2 para buscar un registro dentro de la BD");
            System.out.println("Ingresa 3 para borrar un campo");
            System.out.println("Ingresa 4 para actualizar un campo");
            System.out.println("Ingresa 5 para agregar una herramienta");
            menu = sc.nextInt();

            DaoHerramienta herramientaDao = new DaoHerramienta(conexion);
            Herramienta herramienta;
            Herramienta herramientaActualizada;
            String nombre;
            int cantidad;
            String categoria;

            switch (menu){
                case 1:
                    List<Herramienta> herramientas =  herramientaDao.getAll();
                    System.out.println(herramientas);
                    break;
                case 2:
                    System.out.println("Ingresa el id a buscar");
                    id = sc.nextInt();
                    herramienta = new Herramienta(id);
                    System.out.println(herramientaDao.get(herramienta));
                    break;
                case 3:
                    System.out.println("Ingresa un id de herramienta para borrar");
                    id = sc.nextInt();
                    herramienta = new Herramienta(id);
                    System.out.println("Herramienta con "+ id + " se borro?"+ herramientaDao.delete(herramienta));
                    break;
                case 4:
                    System.out.println("Ingresa el id de la herramienta a actuzalizar: ");
                    id = sc.nextInt();
                    herramienta = new Herramienta(id);
                    System.out.println("Ingresa la nueva cantida de la herramienta: ");
                    cantidad = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Ingresa el nuevo nombre de la herramienta: ");
                    nombre = sc.nextLine();
                    System.out.println("Ingresa la nueva categora de la herramienta: ");
                    categoria = sc.nextLine();

                    herramientaActualizada = new Herramienta(nombre, cantidad, categoria);
                    System.out.println("Se actualizo la nueva herramienta? " + herramientaDao.update(herramienta, herramientaActualizada));
                    break;
                case 5:

                    System.out.println("Ingresa la cantidad de herramientas");
                    cantidad = sc.nextInt();
                    sc.nextLine();// Limpia el scanner hasta el salto de linea
                    System.out.println("Ingresa el nombre de la herramienta: ");
                    nombre = sc.nextLine();
                    System.out.println("Ingresa la categoria de la herramienta");
                    categoria = sc.nextLine();
                    herramienta =  new Herramienta(nombre, cantidad, categoria);
                    System.out.println("Herramienta se creo exitosamente? " + herramientaDao.add(herramienta));
                    break;
                default:
                    System.out.println("Opcion no valida");

            }

        }catch(SQLException exception){
            System.out.println("Conexion no cerrada");

        }catch (DaoException daoException){
            System.out.println(daoException.getMessage());
        }

    }
}
