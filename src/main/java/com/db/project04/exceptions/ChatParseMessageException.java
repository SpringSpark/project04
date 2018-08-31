package com.db.project04.exceptions;

public class ChatParseMessageException extends ChatParseCommandException {
    public ChatParseMessageException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChatParseMessageException(String message) {
        super(message);
    }
}
