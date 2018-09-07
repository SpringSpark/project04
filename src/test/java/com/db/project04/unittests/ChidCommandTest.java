package com.db.project04.unittests;

import com.db.project04.command.ChidCommand;
import com.db.project04.command.RobustSendMessageCommand;
import com.db.project04.exceptions.ChatMessageException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChidCommandTest {
    private final String CORRECT_USERNAME = "test_username";
    private final String USERNAME_WITH_SPACE = "test username";
    private final String TOO_LONG_USERNAME = "testesttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt";
    private final String CORRECT_USERNAME_WITH_SPACES = "   testestttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt";

    @Test
    public void shouldSetUsernameWhenUsernameIsFine() throws ChatMessageException {
        ChidCommand sut = new ChidCommand(CORRECT_USERNAME);
    }

    @Test (expected = ChatMessageException.class)
    public void shouldThrowChatMessageExceptionWhenUsernameContainsSpace() throws ChatMessageException {
        ChidCommand sut = new ChidCommand(USERNAME_WITH_SPACE);
    }

    @Test (expected = ChatMessageException.class)
    public void shouldThrowChatMessageExceptionWhenUsernameIsNull() throws ChatMessageException {
        ChidCommand sut = new ChidCommand(null);
    }

    @Test (expected = ChatMessageException.class)
    public void shouldThrowChatMessageExceptionWhenUsernameIsEmpty() throws ChatMessageException {
        ChidCommand sut = new ChidCommand("");
    }

    @Test (expected = ChatMessageException.class)
    public void shouldThrowChatMessageExceptionWhenUsernameContainsOnlyBlankSpaces() throws ChatMessageException {
        ChidCommand sut = new ChidCommand("         ");
    }

    @Test (expected = ChatMessageException.class)
    public void shouldThrowChatMessageExceptionWhenUsernameIsLongerThan150() throws ChatMessageException {
        ChidCommand sut = new ChidCommand(TOO_LONG_USERNAME);
    }

    @Test
    public void shouldSetUsernameWhenTrimmedUsernameIsFine() throws ChatMessageException {
        ChidCommand sut = new ChidCommand(CORRECT_USERNAME_WITH_SPACES);
    }

    @Test
    public void getChidCommandName() throws ChatMessageException {
        ChidCommand sut = new ChidCommand(CORRECT_USERNAME);
        assertEquals("chid", sut.getCommandName());
    }

    @Test
    public void getChidHandledString() throws ChatMessageException {
        ChidCommand sut = new ChidCommand(CORRECT_USERNAME);
        assertEquals(CORRECT_USERNAME, sut.getHandledString());
    }
}
