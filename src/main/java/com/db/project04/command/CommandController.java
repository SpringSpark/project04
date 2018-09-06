package com.db.project04.command;

import com.db.project04.exceptions.*;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class which is needed to parse raw commands (e.g. from console) which come as input
 * and create concrete Chat command (now supports getting history).
 */

public class CommandController {

    public static final Pattern COMMAND_STRING_PATTERN = Pattern.compile("\\/([A-Za-z]+)(.*)");

    public static ChatCommand parseCommand(String rawString) throws ChatParseCommandException {

        Matcher commandStringPatternMatcher = COMMAND_STRING_PATTERN.matcher(rawString);
        if (commandStringPatternMatcher.matches()) {
           return createConcreteChatCommand(commandStringPatternMatcher, false);
        }
        throw new ChatParseCommandFormatException("command has incorrect format");
    }

    public static ChatCommand parseCommand(String rawString, boolean onServer) throws ChatParseCommandException {

        Matcher commandStringPatternMatcher = COMMAND_STRING_PATTERN.matcher(rawString);
        if (commandStringPatternMatcher.matches()) {
            return createConcreteChatCommand(commandStringPatternMatcher, onServer);
        }
        throw new ChatParseCommandFormatException("command has incorrect format");
    }


    private static ChatCommand createConcreteChatCommand(Matcher commandStringPatternMatcher, boolean onServer)
            throws ChatParseCommandException {
        String commandStringName = commandStringPatternMatcher.group(1);
        if (Objects.equals(commandStringName, HistoryCommand.COMMAND_NAME)) {
            return new HistoryCommand();
        }
        if (Objects.equals(commandStringName, RobustSendMessageCommand.COMMAND_NAME)) {
            try {
                String messageText = commandStringPatternMatcher.group(2);
                return (
                        onServer?
                                new SendMessageCommand(messageText)
                                : new RobustSendMessageCommand(messageText));
            } catch (ChatMessageException e) {
                throw new ChatParseMessageException("message had incorrect format", e);
            }
        }
        if (Objects.equals(commandStringName, ClientShutdownCommand.COMMAND_NAME)) {
            return  new ClientShutdownCommand();
        }
        throw new ChatParseCommandTypeException("unable to parse command type");
    }

    private CommandController(){}
}
