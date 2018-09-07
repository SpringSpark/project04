package com.db.project04.command;


public class HistoryCommand implements ChatCommand {
    public static final String COMMAND_NAME= "hist";

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }
}
