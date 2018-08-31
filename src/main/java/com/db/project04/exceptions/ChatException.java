package com.db.project04.exceptions;

public class ChatException extends  Exception {
    public ChatException(String message, Throwable cause){
        super (message, cause);
    }

    public ChatException(String message){
        super (message);
    }

}
