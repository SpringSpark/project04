package com.db.project04.unittests;

import com.db.project04.message.ServerMessage;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServerMessageTest {
    private static String sutMessage = "sut_message";
    private static String sutDateTime = "2018/09/07 10:51:56";
    private ServerMessage sut = new ServerMessage(sutMessage, sutDateTime);

    @Test
    public void shouldSetTime()
    {
        String testTime = "2010/10/10 10:52:23";
        sut.setDateTime(testTime);
        assertEquals(sut.getDateTime(), testTime);
    }

    @Test
    public void shouldSetText()
    {
        String testText = "test_message";
        sut.setText(testText);
        assertEquals(sut.getText(), testText);
    }

    @Test
    public void shouldConvertToString()
    {
        assertEquals(sut.toString(), sutDateTime + " " + sutMessage);
    }
}
