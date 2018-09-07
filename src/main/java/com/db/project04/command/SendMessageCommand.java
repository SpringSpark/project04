package com.db.project04.command;

import com.db.project04.exceptions.ChatMessageException;


public class SendMessageCommand implements ChatCommand {

    private String handledString;
    public static final String COMMAND_NAME= "snd";

    public SendMessageCommand(String messageString) throws ChatMessageException {
        handledString = messageString;
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

    protected SendMessageCommand() {}


    public String getHandledString() {
        return handledString;
    }
}
