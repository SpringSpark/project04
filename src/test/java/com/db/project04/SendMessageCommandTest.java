package com.db.project04;

import com.db.project04.command.SendMessageCommand;
import com.db.project04.exceptions.ChatMessageException;
import org.junit.Ignore;
import org.junit.Test;

public class SendMessageCommandTest {

    @Test (expected = ChatMessageException.class)
    public void shouldThrowChatMessageExceptionWhenMessageIsNull() throws ChatMessageException {
        SendMessageCommand sut = new SendMessageCommand(null);
    }
}
