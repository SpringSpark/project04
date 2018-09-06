package com.db.project04.command;

import com.db.project04.exceptions.*;

import java.util.Objects;
import java.util.regex.Matcher;

import static java.util.Objects.isNull;

public class ServerCommandController extends CommandController{
    public static ChatCommand parseCommand(String rawString) throws ChatException {

        Matcher commandStringPatternMatcher = COMMAND_STRING_PATTERN.matcher(rawString);
        if (commandStringPatternMatcher.matches()) {
            return createConcreteChatCommand(commandStringPatternMatcher);
        }
        throw new ChatParseCommandFormatException("command has incorrect format");
    }

    private static ChatCommand createConcreteChatCommand(Matcher commandStringPatternMatcher)
            throws ChatException {
        String commandStringName = commandStringPatternMatcher.group(1);
        if (Objects.equals(commandStringName, HistoryCommand.COMMAND_NAME)) {
            return new HistoryCommand();
        }
        if (Objects.equals(commandStringName, RobustSendMessageCommand.COMMAND_NAME)) {
                String messageText = commandStringPatternMatcher.group(2);
                return (new SendMessageCommand(messageText));
        }
        if (Objects.equals(commandStringName, ClientShutdownCommand.COMMAND_NAME)) {
            return  new ClientShutdownCommand();
        }
        throw new ChatParseCommandTypeException("unable to parse command type");
    }
}
