package com.db.project04.unittests;

import com.db.project04.SysoutCaptureAndAssertionAbility;
import com.db.project04.command.*;
import com.db.project04.exceptions.ChatParseCommandException;
import com.db.project04.exceptions.ChatParseCommandFormatException;
import com.db.project04.exceptions.ChatParseCommandTypeException;
import com.db.project04.exceptions.ChatParseMessageException;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ClientCommandControllerTest  implements SysoutCaptureAndAssertionAbility {

    @Test
    public void shouldCreateHistoryCommand() throws ChatParseCommandException {
        ChatCommand resultCommand = ClientCommandController.parseCommand("/hist", "1");
        assertTrue(resultCommand instanceof HistoryCommand);
    }

    @Test
    public void shouldCreateChidCommand() throws ChatParseCommandException {
        ChatCommand resultCommand = ClientCommandController.parseCommand("/chid name", "1");
        assertTrue(resultCommand instanceof ChidCommand);
    }

    @Test (expected = ChatParseMessageException.class)
    public void shouldThrowExceprionForIncorrectUsername() throws ChatParseCommandException {
        ChatCommand resultCommand = ClientCommandController.parseCommand("/chid name name", "1");
    }

    @Test (expected = ChatParseCommandException.class)
    public void shouldThrowExceprionWhenUsernameIsEmpty() throws ChatParseCommandException {
        ChatCommand resultCommand = ClientCommandController.parseCommand("/hist", "");
    }

    @Test (expected = ChatParseCommandException.class)
    public void shouldThrowExceprionWhenUsernameIsEmptyWhenTrimmed() throws ChatParseCommandException {
        ChatCommand resultCommand = ClientCommandController.parseCommand("/hist", "  ");
    }

    @Test (expected = ChatParseCommandException.class)
    public void shouldThrowExceprionWhenUsernameIsNull() throws ChatParseCommandException {
        ChatCommand resultCommand = ClientCommandController.parseCommand("/hist", null);
    }

    @Test
    public void shouldCreateSendMessageCommand() throws ChatParseCommandException {
        ChatCommand resultCommand = ClientCommandController.parseCommand("/snd test message", "1");
        assertTrue(resultCommand instanceof RobustSendMessageCommand);
    }

    @Test(expected = ChatParseMessageException.class)
    public void shouldThrowChatMessageExceptionWhenMessageIsEmpty() throws ChatParseCommandException {
        ChatCommand resultCommand = ClientCommandController.parseCommand("/snd ", "1");
    }

    @Test(expected = ChatParseCommandTypeException.class)
    public void shouldThrowChatMessageExceptionWhenUndefinedCommand() throws ChatParseCommandException {
        ChatCommand resultCommand = ClientCommandController.parseCommand("/wrong ", "1");
    }

    @Test(expected = ChatParseCommandFormatException.class)
    public void shouldThrowChatMessageExceptionWhenWrongInput() throws ChatParseCommandException {
        ChatCommand resultCommand = ClientCommandController.parseCommand("wrong ", "1");
    }

    @Test
    public void shouldCreateShutdownCommand() throws ChatParseCommandException {
        ChatCommand resultCommand = ClientCommandController.parseCommand("/quit", "1");
        assertTrue(resultCommand instanceof ClientShutdownCommand);
    }
}
