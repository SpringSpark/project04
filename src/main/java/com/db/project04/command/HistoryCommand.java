package com.db.project04.command;

import com.db.project04.exceptions.ChatMessageException;

public class HistoryCommand implements ChatCommand {
    public static final String COMMAND_NAME= "hist";

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }
}
