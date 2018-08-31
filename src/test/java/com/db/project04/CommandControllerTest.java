package com.db.project04;

import com.db.project04.command.ChatCommand;
import com.db.project04.command.CommandController;
import com.db.project04.command.HistoryCommand;
import com.db.project04.command.SendMessageCommand;
import com.db.project04.exceptions.ChatMessageException;
import com.db.project04.exceptions.ChatParseCommandException;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CommandControllerTest {

    private CommandController sut = new CommandController();

    @Ignore
    @Test
    public void shouldCreateHistoryCommand() throws ChatParseCommandException {
        ChatCommand resultCommand = sut.parseCommand("/hist");
        assertTrue(resultCommand instanceof HistoryCommand);
    }

    @Ignore
    @Test
    public void shouldCreateSendMessageCommand() throws ChatParseCommandException {
        ChatCommand resultCommand = sut.parseCommand("/snd test message");
        assertTrue(resultCommand instanceof SendMessageCommand);
    }

    @Ignore
    @Test(expected = ChatMessageException.class)
    public void shouldThrowChatMessageExceptionWhenMessageIsEmpty() throws ChatParseCommandException {
        ChatCommand resultCommand = sut.parseCommand("/snd ");
    }

    @Ignore
    @Test(expected = ChatMessageException.class)
    public void shouldThrowChatMessageExceptionWhenUndefinedCommand() throws ChatParseCommandException {
        ChatCommand resultCommand = sut.parseCommand("/wrong ");
    }
}
