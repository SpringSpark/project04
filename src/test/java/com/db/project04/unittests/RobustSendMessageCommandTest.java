package com.db.project04.unittests;

import com.db.project04.command.RobustSendMessageCommand;
import com.db.project04.exceptions.ChatMessageException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RobustSendMessageCommandTest {

    private final String CORRECT_STRING = "test string";
    private final String TOO_LONG_STRING = "testesttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt";
    private final String CORRECT_STRING_WITH_SPACES = "   testestttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt";

    @Test
    public void shouldCreateSendMessageCommandWhendMessageIsFine() throws ChatMessageException {
        RobustSendMessageCommand sut = new RobustSendMessageCommand(CORRECT_STRING);
        assertEquals(sut.getHandledString(), CORRECT_STRING);
    }

    @Test (expected = ChatMessageException.class)
    public void shouldThrowChatMessageExceptionWhenMessageIsNull() throws ChatMessageException {
        RobustSendMessageCommand sut = new RobustSendMessageCommand(null);
    }

    @Test (expected = ChatMessageException.class)
    public void shouldThrowChatMessageExceptionWhenMessageIsEmpty() throws ChatMessageException {
        RobustSendMessageCommand sut = new RobustSendMessageCommand("");
    }

    @Test (expected = ChatMessageException.class)
    public void shouldThrowChatMessageExceptionWhenMessageContainsOnlyBlankSpaces() throws ChatMessageException {
        RobustSendMessageCommand sut = new RobustSendMessageCommand("         ");
    }

    @Test (expected = ChatMessageException.class)
    public void shouldThrowChatMessageExceptionWhenMessageIsLongerThan150() throws ChatMessageException {
        RobustSendMessageCommand sut = new RobustSendMessageCommand(TOO_LONG_STRING);
    }

    @Test
    public void shouldCreateSendMessageCommandWhenTrimmedMessageIsFine() throws ChatMessageException {
        RobustSendMessageCommand sut = new RobustSendMessageCommand(CORRECT_STRING_WITH_SPACES);
        assertEquals(sut.getHandledString(), CORRECT_STRING_WITH_SPACES.trim());
    }

}
