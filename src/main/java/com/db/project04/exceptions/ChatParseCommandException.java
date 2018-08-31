package com.db.project04.exceptions;

public class ChatParseCommandException extends ChatException {
    public ChatParseCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChatParseCommandException(String message) {
        super(message);
    }
}
