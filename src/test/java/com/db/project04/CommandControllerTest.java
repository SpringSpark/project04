package com.db.project04;

import com.db.project04.command.*;
import com.db.project04.exceptions.ChatParseCommandException;
import com.db.project04.exceptions.ChatParseCommandTypeException;
import com.db.project04.exceptions.ChatParseMessageException;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CommandControllerTest  implements SysoutCaptureAndAssertionAbility  {

    //private CommandController sut = new CommandController();

    @Test
    public void shouldCreateHistoryCommand() throws ChatParseCommandException {
        ChatCommand resultCommand = ClientCommandController.parseCommand("/hist", "1");
        assertTrue(resultCommand instanceof HistoryCommand);
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
}
