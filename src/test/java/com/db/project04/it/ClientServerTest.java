package com.db.project04.it;

import com.db.project04.SysoutCaptureAndAssertionAbility;
import com.db.project04.client.Client;
import com.db.project04.command.ChatCommand;
import com.db.project04.command.CommandController;
import com.db.project04.exceptions.ChatClientException;
import com.db.project04.exceptions.ChatParseCommandException;
import com.db.project04.server.ServerMain;
import org.apache.maven.settings.Server;
import org.junit.Test;

import java.io.IOException;

public class ClientServerTest implements SysoutCaptureAndAssertionAbility {
    private Client client = new Client();
    private ServerMain server = new ServerMain();

    public ClientServerTest() throws ChatClientException {
    }

    @Test
    public void serverShouldCreateNewConnectionForClient() throws IOException {
        server.start();
        String testString = "/snd test";
        client.send(testString);
        //assertSysoutContains("New connection");
    }
}
