package com.db.project04.command;

import com.db.project04.exceptions.ChatMessageException;

import static java.util.Objects.isNull;

/**
 * Class for handling authentication.
 */

public class ChidCommand implements ChatCommand{
    public static final String COMMAND_NAME= "chid";
    public static final int MAX_LENGTH = 150;
    private final String handledString;

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

    public ChidCommand(String messageString) throws ChatMessageException {
        if(isNull(messageString) || messageString.isEmpty() || messageString.trim().isEmpty()) {
            throw new ChatMessageException("empty username");
        }
        String resultMessageString = messageString.trim();
        if(resultMessageString.length() > MAX_LENGTH) {
            throw new ChatMessageException("username is longer than 150 characters");
        }
        if(resultMessageString.contains(" ")) {
            throw new ChatMessageException("invalid username");
        }
        handledString = resultMessageString;
    }

    public String getHandledString() {
        return handledString;
    }
}
