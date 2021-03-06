package com.db.project04.command;

import com.db.project04.exceptions.ChatMessageException;
import com.db.project04.exceptions.ChatParseCommandException;
import com.db.project04.exceptions.ChatParseCommandFormatException;
import com.db.project04.exceptions.ChatParseMessageException;
import com.db.project04.exceptions.ChatParseCommandTypeException;
import java.util.Objects;
import java.util.regex.Matcher;
import static java.util.Objects.isNull;

/**
 * Class with static functionality for parsing commands from strings.
 */

public final class ClientCommandController extends CommandController{

    private ClientCommandController() {
    }

    public static ChatCommand parseCommand(String rawString, String username) throws ChatParseCommandException {

        Matcher commandStringPatternMatcher = COMMAND_STRING_PATTERN.matcher(rawString);
        if (commandStringPatternMatcher.matches()) {
            return createConcreteChatCommand(commandStringPatternMatcher, username);
        }
        throw new ChatParseCommandFormatException("command has incorrect format");
    }

    private static ChatCommand createConcreteChatCommand(Matcher commandStringPatternMatcher, String username)
            throws ChatParseCommandException {
        String commandStringName = commandStringPatternMatcher.group(1);

        if (Objects.equals(commandStringName, ChidCommand.COMMAND_NAME)){
            try {
                String messageText = commandStringPatternMatcher.group(2);
                return new ChidCommand(messageText);
            } catch (ChatMessageException e) {
                throw new ChatParseMessageException("username has incorrect format", e);
            }
        }
        if(isNull(username)  || username.isEmpty() || username.trim().isEmpty())
        {
            throw new ChatParseCommandException("you must login before sending message");
        }
        if (Objects.equals(commandStringName, HistoryCommand.COMMAND_NAME)) {
            return new HistoryCommand();
        }
        if (Objects.equals(commandStringName, RobustSendMessageCommand.COMMAND_NAME)) {
            try {
                String messageText = commandStringPatternMatcher.group(2);
                return (new RobustSendMessageCommand(messageText));
            } catch (ChatMessageException e) {
                throw new ChatParseMessageException("message has incorrect format", e);
            }
        }
        if (Objects.equals(commandStringName, ClientShutdownCommand.COMMAND_NAME)) {
            return  new ClientShutdownCommand();
        }
        throw new ChatParseCommandTypeException("unable to parse command type");
    }
}
