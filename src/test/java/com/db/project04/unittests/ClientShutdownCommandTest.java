package com.db.project04.unittests;

import com.db.project04.command.ClientShutdownCommand;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClientShutdownCommandTest {
    ClientShutdownCommand sut = new ClientShutdownCommand();

    @Test
    public void shouldReturnCommandName(){
        String testCommandName = "quit";
        assertEquals(sut.getCommandName(), testCommandName);
    }

    @Test
    public void shouldConvertToString(){
        String testString = String.format("\\/%s", "quit");
        assertEquals(sut.toString(), testString);
    }
}
