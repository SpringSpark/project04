package com.db.project04.command;

import com.db.project04.exceptions.*;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

public class ServerCommandController extends CommandController{

    public static final Pattern SND_COMMAND_STRING_PATTERN = Pattern.compile("(\\S+) (.+)");
    public static final Pattern COMMAND_STRING_PATTERN = Pattern.compile("\\/([A-Za-z]+)(.*)");

    //(.+) (.+)
    public static ChatCommand parseCommand(String rawString) throws ChatException {

        System.out.println("lets's parse string " + rawString);
        Matcher sndCommandStringPatternMatcher = SND_COMMAND_STRING_PATTERN.matcher(rawString);
        if(sndCommandStringPatternMatcher.matches())
        {
            String rawStringWithoutUsername = sndCommandStringPatternMatcher.group(2);
            System.out.println("it's send command; let's parse " + rawStringWithoutUsername);
            return matchString(rawStringWithoutUsername, true);
        }
        else {
            return matchString(rawString, false);
        }
    }

    public static ChatCommand matchString(String rawString, boolean snd) throws ChatException {
        Matcher commandStringPatternMatcher = COMMAND_STRING_PATTERN.matcher(rawString);
        if (commandStringPatternMatcher.matches()) {
            if(snd == true)
            {
                return createSndChatCommand(commandStringPatternMatcher);
            }
            else {
                return createConcreteChatCommand(commandStringPatternMatcher);
            }
        }
        throw new ChatParseCommandFormatException("command has incorrect format");
    }

    private static ChatCommand createConcreteChatCommand(Matcher commandStringPatternMatcher)
            throws ChatException {
        String commandStringName = commandStringPatternMatcher.group(1);
        if (Objects.equals(commandStringName, HistoryCommand.COMMAND_NAME)) {
            return new HistoryCommand();
        }
        if (Objects.equals(commandStringName, ClientShutdownCommand.COMMAND_NAME)) {
            return  new ClientShutdownCommand();
        }
        throw new ChatParseCommandTypeException("unable to parse command type");
    }

    private static ChatCommand createSndChatCommand(Matcher commandStringPatternMatcher)
            throws ChatException {
        String commandStringName = commandStringPatternMatcher.group(1);
        if (Objects.equals(commandStringName, RobustSendMessageCommand.COMMAND_NAME)) {
            String messageText = commandStringPatternMatcher.group(2);
            return (new SendMessageCommand(messageText));
        }
        throw new ChatParseCommandTypeException("unable to parse command type");
    }
}
