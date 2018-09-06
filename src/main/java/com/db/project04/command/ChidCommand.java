package com.db.project04.command;

import com.db.project04.exceptions.ChatMessageException;

import static java.util.Objects.isNull;

public class ChidCommand implements ChatCommand{
    public static final String COMMAND_NAME= "chid";
    public static final int MAX_LENGTH = 150;
    private String handledString;

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

    public ChidCommand(String messageString) throws ChatMessageException {
        if(isNull(messageString) || messageString.isEmpty() || messageString.trim().isEmpty()) {
            throw new ChatMessageException("empty username");
        }
        messageString = messageString.trim();
        if(messageString.length() > MAX_LENGTH) {
            throw new ChatMessageException("username is longer than 150 characters");
        }
        handledString = messageString;
    }

    public String getHandledString() {
        return handledString;
    }
}
