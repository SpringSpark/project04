package com.db.project04.unittests;

import com.db.project04.command.HistoryCommand;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HistoryCommandTest {
    HistoryCommand sut = new HistoryCommand();

    @Test
    public void shouldReturnCommandName(){
        String testCommandName = "hist";
        assertEquals(sut.getCommandName(), testCommandName);
    }
}
