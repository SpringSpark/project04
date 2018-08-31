package com.db.project04.exceptions;

public class ChatParseCommandFormatException extends ChatParseCommandException{
    public ChatParseCommandFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChatParseCommandFormatException(String message) {
        super(message);
    }
}
