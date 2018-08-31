package com.db.project04.command;

import com.db.project04.exceptions.ChatParseCommandException;
import com.db.project04.exceptions.ChatParseCommandFormatException;
import com.db.project04.exceptions.ChatParseCommandTypeException;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandController {

    public ChatCommand parseCommand(String rawString) throws ChatParseCommandException {
        Pattern commandStringPattern = Pattern.compile("\\\\([A-Za-z]+)(.*)");
        Matcher commandStringPatternMatcher = commandStringPattern.matcher(rawString);
        if (commandStringPatternMatcher.matches()) {
            String commandStringName = commandStringPatternMatcher.group(1);
            if (Objects.equals(commandStringName, "hist")) {
                return new HistoryCommand();
            }
            if (Objects.equals(commandStringName, "snd")) {
                return new SendMessageCommand(commandStringPatternMatcher.group(2));
            }
            throw new ChatParseCommandTypeException("unable to parse command type");
        }
        throw new ChatParseCommandFormatException("command has incorrect format");
    }
}
