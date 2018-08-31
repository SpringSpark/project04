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

    public ChatCommand parseCommand(String rawString) throws ChatParseCommandException {
        Pattern commandStringPattern = Pattern.compile("\\/([A-Za-z]+)(.*)");
        Matcher commandStringPatternMatcher = commandStringPattern.matcher(rawString);
        if (commandStringPatternMatcher.matches()) {
           return createConcreteChatCommand(commandStringPatternMatcher);
        }
        throw new ChatParseCommandFormatException("command has incorrect format");
    }

    private ChatCommand createConcreteChatCommand(Matcher commandStringPatternMatcher)
            throws ChatParseCommandException {
        String commandStringName = commandStringPatternMatcher.group(1);
        if (Objects.equals(commandStringName, CommandType.HIST.getCommandText())) {
            return new HistoryCommand();
        }
        if (Objects.equals(commandStringName, CommandType.SND.getCommandText())) {
            try {
                return new SendMessageCommand(commandStringPatternMatcher.group(2));
            } catch (ChatMessageException e) {
                throw new ChatParseMessageException("message had incorrect format", e);
            }
        }
        throw new ChatParseCommandTypeException("unable to parse command type");
    }
}
