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
           return createConcreteChatCommand(commandStringPatternMatcher);
        }
        throw new ChatParseCommandFormatException("command has incorrect format");
    }

    private static ChatCommand createConcreteChatCommand(Matcher commandStringPatternMatcher)
            throws ChatParseCommandException {
        String commandStringName = commandStringPatternMatcher.group(1);
        if (Objects.equals(commandStringName, HistoryCommand.COMMAND_NAME)) {
            return new HistoryCommand();
        }
        if (Objects.equals(commandStringName, RobustSendMessageCommand.COMMAND_NAME)) {
            try {
                return new RobustSendMessageCommand(commandStringPatternMatcher.group(2));
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
