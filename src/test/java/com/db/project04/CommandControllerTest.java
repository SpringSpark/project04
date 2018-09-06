package com.db.project04;

import com.db.project04.command.ChatCommand;
import com.db.project04.command.CommandController;
import com.db.project04.command.HistoryCommand;
import com.db.project04.command.RobustSendMessageCommand;
import com.db.project04.exceptions.ChatParseCommandException;
import com.db.project04.exceptions.ChatParseCommandTypeException;
import com.db.project04.exceptions.ChatParseMessageException;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CommandControllerTest {

    //private CommandController sut = new CommandController();

    @Test
    public void shouldCreateHistoryCommand() throws ChatParseCommandException {
        ChatCommand resultCommand = CommandController.parseCommand("/hist");
        assertTrue(resultCommand instanceof HistoryCommand);
    }

    @Test
    public void shouldCreateSendMessageCommand() throws ChatParseCommandException {
        ChatCommand resultCommand = CommandController.parseCommand("/snd test message");
        assertTrue(resultCommand instanceof RobustSendMessageCommand);
    }

    @Test(expected = ChatParseMessageException.class)
    public void shouldThrowChatMessageExceptionWhenMessageIsEmpty() throws ChatParseCommandException {
        ChatCommand resultCommand = CommandController.parseCommand("/snd ");
    }

    @Test(expected = ChatParseCommandTypeException.class)
    public void shouldThrowChatMessageExceptionWhenUndefinedCommand() throws ChatParseCommandException {
        ChatCommand resultCommand = CommandController.parseCommand("/wrong ");
    }
}
