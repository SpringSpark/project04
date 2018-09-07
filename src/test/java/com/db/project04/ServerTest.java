package com.db.project04;

import com.db.project04.server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ServerTest implements SysoutCaptureAndAssertionAbility{
     //region given
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
    }

    @After
    public void tearDown() {
        resetOut();
    }
    //endregion

    @Test
    public void smokeServerTest() throws IOException {
        Server server = new Server();
        server.start();
        server.stop();
        assertSysoutContains("We are alive");
    }
}
