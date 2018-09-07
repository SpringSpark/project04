package com.db.project04.command;

import com.db.project04.exceptions.ChatMessageException;

import static java.util.Objects.isNull;

/**
 * Class which is used by Client and checks text message for correctness.
 */

public class RobustSendMessageCommand extends SendMessageCommand {

    public static final int MAX_LENGTH = 150;
    private String handledString;

    public RobustSendMessageCommand(String messageString) throws ChatMessageException {
        if(isNull(messageString) || messageString.isEmpty() || messageString.trim().isEmpty()) {
            throw new ChatMessageException("empty input message");
        }
        messageString = messageString.trim();
        if(messageString.length() > MAX_LENGTH) {
            throw new ChatMessageException("input message is longer than 150 characters");
        }
        handledString = messageString;
    }

    @Override
    public String getHandledString() {
        return handledString;
    }
}
