package com.db.project04.unittests;

import com.db.project04.exceptions.FileException;
import com.db.project04.message.ServerMessage;
import com.db.project04.server.messagehistory.SimpleMessageHistory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class SimpleMessageHistoryTest {
    SimpleMessageHistory sut = new SimpleMessageHistory();

    public SimpleMessageHistoryTest() throws FileException {
    }

    @Test
    public void shouldAddNewMessageToHistory(){
        ServerMessage serverMessage = mock(ServerMessage.class);
        sut.addNewMessage(serverMessage);
        List<ServerMessage> testHistory = sut.getMessageHistory();
        assertEquals(1, testHistory.size());
    }
}
