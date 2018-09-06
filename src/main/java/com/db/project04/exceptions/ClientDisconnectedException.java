package com.db.project04.exceptions;

public class ClientDisconnectedException extends ChatException {
    public ClientDisconnectedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientDisconnectedException(String message) {
        super(message);
    }
}
