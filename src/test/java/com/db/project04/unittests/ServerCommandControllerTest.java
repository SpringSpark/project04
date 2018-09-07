package com.db.project04.unittests;

import com.db.project04.command.*;
import com.db.project04.exceptions.ChatException;
import com.db.project04.exceptions.ChatParseCommandException;
import com.db.project04.exceptions.ChatParseCommandTypeException;
import com.db.project04.exceptions.ChatParseMessageException;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ServerCommandControllerTest {
    private final String username = "username";

    @Test
    public void shouldCreateHistoryCommand() throws ChatException {
        ChatCommand resultCommand = ServerCommandController.parseCommand(username + " " + "/hist");
        assertTrue(resultCommand instanceof HistoryCommand);
    }

    @Test
    public void shouldCreateSendMessageCommand() throws ChatException {
        ChatCommand resultCommand = ServerCommandController.parseCommand(username + " " + "/snd test message");
        assertTrue(resultCommand instanceof SendMessageCommand);
    }

    @Test(expected = ChatException.class)
    public void shouldThrowChatMessageExceptionWhenMessageIsEmpty() throws ChatException {
        ChatCommand resultCommand = ServerCommandController.parseCommand(username + " " + "snd ");
    }

    @Test(expected = ChatParseCommandTypeException.class)
    public void shouldThrowChatMessageExceptionWhenUndefinedCommand() throws ChatException {
        ChatCommand resultCommand = ServerCommandController.parseCommand(username + " " + "/wrong ");
    }

    @Test(expected = ChatException.class)
    public void shouldThrowChatMessageExceptionWhenUsernameIsEmpty() throws ChatException {
        ChatCommand resultCommand = ServerCommandController.parseCommand("/snd ");
    }

    @Test(expected = ChatException.class)
    public void shouldThrowChatMessageExceptionWhenUsernameIsEmptyAndCommandIsWrong() throws ChatException {
        ChatCommand resultCommand = ServerCommandController.parseCommand("/wrong ");
    }

    @Test
    public void shouldCreateShutdownCommand() throws ChatException {
        ChatCommand resultCommand = ServerCommandController.parseCommand(username + " " + "/quit");
        assertTrue(resultCommand instanceof ClientShutdownCommand);
    }
}
