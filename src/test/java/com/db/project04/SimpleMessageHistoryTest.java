package com.db.project04;

import com.db.project04.message.ServerMessage;
import com.db.project04.server.messagehistory.SimpleMessageHistory;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class SimpleMessageHistoryTest {
    SimpleMessageHistory sut = new SimpleMessageHistory();

    @Test
    public void shouldAddNewMessageToHistory(){
        ServerMessage serverMessage = mock(ServerMessage.class);
        sut.addNewMessage(serverMessage);
        ArrayList<ServerMessage> testHistory = sut.getMessageHistory();
        assertEquals(1, testHistory.size());
    }
}
