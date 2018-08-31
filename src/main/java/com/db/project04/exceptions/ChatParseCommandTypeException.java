package com.db.project04.exceptions;

public class ChatParseCommandTypeException extends ChatParseCommandException {
    public ChatParseCommandTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChatParseCommandTypeException (String message) {
        super(message);
    }
}
