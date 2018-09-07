package com.db.project04.unittests;

import com.db.project04.SysoutCaptureAndAssertionAbility;
import com.db.project04.server.Session;
import com.db.project04.server.messagehistory.MessageHistory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.Socket;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class SessionTest implements SysoutCaptureAndAssertionAbility {

    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
    }

    @After
    public void tearDown() {
        resetOut();
    }

    @Test
    public void shouldCreateSession(){
        Socket mockClient = mock(Socket.class);
        MessageHistory mockMessageHistory = mock(MessageHistory.class);
        Session sut = new Session(mockClient, mockMessageHistory);
        assertEquals(mockClient, sut.getClient());
        assertEquals(mockMessageHistory, sut.getMessageHistory());
    }

    @Test
    public void shouldNotCreateSession(){
        Session sut = new Session(null, null);
        assertSysoutContains("Could not create session");
    }
}
