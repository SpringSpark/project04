package com.db.project04.it;

import com.db.project04.SysoutCaptureAndAssertionAbility;
import com.db.project04.client.Client;
import com.db.project04.exceptions.ChatClientException;
import com.db.project04.server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

public class ClientServerTest  implements SysoutCaptureAndAssertionAbility {

    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
    }

    @After
    public void tearDown() {
        resetOut();
    }

    public ClientServerTest() throws ChatClientException {
    }

    @Test
    public void serverAndClientShouldNotRaiseException() throws IOException, ChatClientException {
        Server server = new Server();
        server.start();
        Client client = new Client("testUsername");
        String testString = "/snd test";
        server.stop();
    }

    @Test @Ignore
    public void serverShouldCreateNewConnections() throws IOException, ChatClientException, InterruptedException {
        Server server = new Server();
        server.start();
        Client client1 = new Client("testUsername1");
        Client client2 = new Client("testUsername2");
        String testString1 = "/snd test1";
        String testString2 = "/snd test2";

        Thread t1 = new Thread(() -> {
            client1.send(testString1);
        });

        Thread t2 = new Thread(() -> {
            client2.send(testString2);
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        assertSysoutContains("New connection");
        assertSysoutContains("New connection");
        server.stop();
    }

}
