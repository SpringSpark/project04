package com.db.project04.command;

public class ClientShutdownCommand implements ChatCommand {
    public static final String COMMAND_NAME= "quit";

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public String toString() {
        return String.format("\\/%s", COMMAND_NAME);
    }
}
