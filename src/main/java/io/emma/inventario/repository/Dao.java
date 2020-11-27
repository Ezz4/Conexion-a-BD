package io.emma.inventario.repository;

import io.emma.inventario.exception.DaoException;

import java.util.List;

public interface Dao <T>{
    List<T> getAll() throws DaoException;
    T get(T item) throws DaoException;
    boolean add(T item) throws DaoException;
    boolean delete(T item) throws DaoException;
    boolean update(T currectItem, T newItem) throws DaoException;
}
