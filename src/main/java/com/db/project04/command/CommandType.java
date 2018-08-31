package com.db.project04.command;

public enum CommandType {
    HIST("hist"),
    SND("snd");

    private String commandText;

    CommandType (String commandText) {
        this.commandText = commandText;
    }

    public String getCommandText (){
        return commandText;
    }
}