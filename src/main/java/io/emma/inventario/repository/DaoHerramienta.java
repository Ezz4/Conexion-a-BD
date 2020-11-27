package io.emma.inventario.repository;

import io.emma.inventario.exception.DaoException;
import io.emma.inventario.model.Herramienta;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class DaoHerramienta implements Dao<Herramienta>{

    private static final String SELECT_ALL = "SELECT * FROM HERRAMIENTAS";
    private static final String SELECT_BY_ID = "SELECT * FROM HERRAMIENTAS WHERE id = ?";
    private static final String INSERT = "INSERT INTO HERRAMIENTAS(nombre, cantidad, categoria) values(?, ?, ?)";
    private static final String DELETE = "DELETE FROM HERRAMIENTAS WHERE id = ?";

    private Connection connection;

    public DaoHerramienta(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<Herramienta> getAll() throws DaoException {
        List<Herramienta> herramientas = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SELECT_ALL);
            statement.executeQuery();
            resultSet = statement.getResultSet();
            while (resultSet.next()) {
                Herramienta herramienta = new Herramienta(resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getInt("cantidad"),
                        resultSet.getString("categoria"));
                herramientas.add(herramienta);
            }
        } catch (SQLException throwables) {
            throw new DaoException("Fail while getting al the tools", throwables);
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return herramientas;
    }

    @Override
    public Herramienta get(Herramienta item) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Herramienta herramienta = null;
        try {
            statement = connection.prepareStatement(SELECT_BY_ID);
            statement.setInt(1, item.getId());
            statement.executeQuery();
            resultSet = statement.getResultSet();
            if (resultSet.next()) {
                herramienta = new Herramienta(resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getInt("cantidad"),
                        resultSet.getString("categoria"));
            }
        } catch (SQLException throwables) {
            throw new DaoException("Fail while getting one tool", throwables);
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return herramienta;
    }

    @Override
    public boolean add(Herramienta herramienta) throws DaoException {
        PreparedStatement statement = null;
        int registosModificador;
        try {
            statement = connection.prepareStatement(INSERT);
            statement.setString(1, herramienta.getNombre());
            statement.setInt(2, herramienta.getCantidad());
            statement.setString(3, herramienta.getCategoria());
            registosModificador = statement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException("Fail while getting al the tools", throwables);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return registosModificador != 0;
    }

    @Override
    public boolean delete(Herramienta item) throws DaoException {
        int registosModificador;
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(DELETE);
            registosModificador = statement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException("Fail while getting al the tools", throwables);
        } finally {
            try {

                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return registosModificador !=0;
    }

    @Override
    public boolean update(Herramienta currectItem, Herramienta newItem) throws DaoException {
        return false;
    }

}
