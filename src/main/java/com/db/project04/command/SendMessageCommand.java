package com.db.project04.command;

import com.db.project04.exceptions.ChatMessageException;

import static java.util.Objects.isNull;

public class SendMessageCommand extends ChatCommand {
    private String handledString;

    public SendMessageCommand(String messageString) throws ChatMessageException {
        if(isNull(messageString) || messageString.isEmpty() || messageString.trim().isEmpty()) {
            throw new ChatMessageException("empty input message");
        }
        messageString = messageString.trim();
        if(messageString.length() > 150) {
            throw new ChatMessageException("input message is longer than 150 characters");
        }
        handledString = messageString;
    }
}
