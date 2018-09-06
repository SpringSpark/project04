package com.db.project04.it;

import com.db.project04.RemoteConfiguration;
import com.db.project04.SysoutCaptureAndAssertionAbility;
import com.db.project04.client.Client;
import com.db.project04.command.ChatCommand;
import com.db.project04.command.CommandController;
import com.db.project04.exceptions.ChatClientException;
import com.db.project04.exceptions.ChatParseCommandException;
import com.db.project04.server.ServerMain;
import com.db.project04.server.Session;
import org.apache.maven.settings.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
        ServerMain server = new ServerMain();
        server.start();
        Client client = new Client();
        String testString = "/snd test";
        //client.send(testString);
        //assertSysoutContains("We are alive");
        //server.stop();
    }
/*
    @Test
    public void serverShouldCreateNewConnections() throws IOException, ChatClientException, InterruptedException {
        ServerMain server = new ServerMain();
        server.start();
        Client client1 = new Client();
        Client client2 = new Client();
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
*/
    /*@Test
    public void bla() throws IOException{
        ServerSocket portListener = new ServerSocket(RemoteConfiguration.PORT_NUMBER);
        Socket clientSession = portListener.accept();
        Session session = new Session(clientSession);
        assertSysoutContains("New connection");
    }*/
}
