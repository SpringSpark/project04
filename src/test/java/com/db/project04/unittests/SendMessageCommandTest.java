package com.db.project04.unittests;

import com.db.project04.command.SendMessageCommand;
import com.db.project04.exceptions.ChatMessageException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SendMessageCommandTest {
    private static String sutMessage = "sut_message";
    SendMessageCommand sut = new SendMessageCommand(sutMessage);

    public SendMessageCommandTest() throws ChatMessageException {
    }

    @Test
    public void shouldReturnCommandName(){
        String testCommandName = "snd";
        assertEquals(sut.getCommandName(), testCommandName);
    }

    @Test
    public void shouldGetHandledString(){
        assertEquals(sut.getHandledString(), sutMessage);
    }
}
