package com.db.project04.exceptions;

public class FileException extends Exception{

    public FileException(String message, Throwable cause){
        super (message, cause);
    }
    public FileException(String message){
        super (message);
    }
}
