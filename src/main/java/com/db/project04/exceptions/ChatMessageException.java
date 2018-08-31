package com.db.project04.exceptions;

public class ChatMessageException extends ChatException {
    public ChatMessageException(String message, Throwable cause) {
        super(message, cause);
    }
    public ChatMessageException(String message) {
        super(message);
    }
}
