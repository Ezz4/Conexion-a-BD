package io.emma.inventario.exception;

public class InventarioServiceException extends Exception{
    public InventarioServiceException() {
    }

    public InventarioServiceException(String message) {
        super(message);
    }

    public InventarioServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
