package com.db.project04;

import com.db.project04.command.SendMessageCommand;
import com.db.project04.exceptions.ChatMessageException;
import org.junit.Ignore;
import org.junit.Test;

public class SendMessageCommandTest {

    private final String CORRECT_STRING = "test string";
    private final String TOO_LONG_STRING = "testesttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt";
    private final String CORRECT_STRING_WITH_SPACES = "   testestttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt";

    @Ignore
    @Test
    public void shouldCreateSendMessageCommandWhendMessageIsFine() throws ChatMessageException {
        SendMessageCommand sut = new SendMessageCommand(CORRECT_STRING);
    }

    @Ignore
    @Test (expected = ChatMessageException.class)
    public void shouldThrowChatMessageExceptionWhenMessageIsNull() throws ChatMessageException {
        SendMessageCommand sut = new SendMessageCommand(null);
    }

    @Ignore
    @Test (expected = ChatMessageException.class)
    public void shouldThrowChatMessageExceptionWhenMessageIsEmpty() throws ChatMessageException {
        SendMessageCommand sut = new SendMessageCommand(" ");
    }

    @Ignore
    @Test (expected = ChatMessageException.class)
    public void shouldThrowChatMessageExceptionWhenMessageContainsOnlyBlankSpaces() throws ChatMessageException {
        SendMessageCommand sut = new SendMessageCommand("         ");
    }

    @Ignore
    @Test (expected = ChatMessageException.class)
    public void shouldThrowChatMessageExceptionWhenMessageIsLongerThan150() throws ChatMessageException {
        SendMessageCommand sut = new SendMessageCommand(TOO_LONG_STRING);
    }

    @Ignore
    @Test
    public void shouldCreateSendMessageCommandWhenTrimmedMessageIsFine() throws ChatMessageException {
        SendMessageCommand sut = new SendMessageCommand(CORRECT_STRING_WITH_SPACES);
    }

}
