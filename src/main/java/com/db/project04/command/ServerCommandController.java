package com.db.project04.command;


import com.db.project04.exceptions.ChatException;
import com.db.project04.exceptions.ChatParseCommandFormatException;
import com.db.project04.exceptions.ChatParseCommandTypeException;

import java.util.Objects;
import java.util.regex.Matcher;

public class ServerCommandController extends CommandController{

    public static ChatCommand parseCommand(String rawString) throws ChatException {

        Matcher sndCommandStringPatternMatcher = PREPARATION_COMMAND_STRING_PATTERN.matcher(rawString);
        if (sndCommandStringPatternMatcher.matches()) {
            String username = sndCommandStringPatternMatcher.group(1);
            String command = sndCommandStringPatternMatcher.group(2);


            Matcher commandStringPatternMatcher = COMMAND_STRING_PATTERN.matcher(command);
            if (commandStringPatternMatcher.matches()) {
                return createConcreteChatCommand(commandStringPatternMatcher, username);
            }
        }
        throw new ChatParseCommandFormatException("command has incorrect format");
    }

    private static ChatCommand createConcreteChatCommand(Matcher commandStringPatternMatcher, String username)
            throws ChatException {
        String commandStringName = commandStringPatternMatcher.group(1);
        if (Objects.equals(commandStringName, RobustSendMessageCommand.COMMAND_NAME)) {
            String messageText = commandStringPatternMatcher.group(2);
            return (new SendMessageCommand(username + messageText));
        }
        if (Objects.equals(commandStringName, HistoryCommand.COMMAND_NAME)) {
            return new HistoryCommand();
        }
        if (Objects.equals(commandStringName, ClientShutdownCommand.COMMAND_NAME)) {
            return  new ClientShutdownCommand();
        }
        throw new ChatParseCommandTypeException("unable to parse command type");
    }
}
