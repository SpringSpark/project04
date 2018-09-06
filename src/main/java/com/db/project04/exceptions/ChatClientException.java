package com.db.project04.exceptions;

public class ChatClientException extends ChatException {
    public ChatClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChatClientException(String message) {
        super(message);
    }
}
