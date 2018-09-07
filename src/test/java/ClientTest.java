import com.db.project04.client.Client;
import com.db.project04.exceptions.ChatClientException;
import com.db.project04.server.Server;
import org.junit.Test;

import java.io.IOException;
import java.net.Socket;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class ClientTest {
    @Test (expected = ChatClientException.class)
    public void clientShouldExitWhenThereIsNoServer() throws ChatClientException {
        Client client = new Client();
    }

    @Test
    public void clientSetUserName() throws IOException, ChatClientException {
        Server server = new Server();
        server.start();
        Client client = new Client();
        client.setUsername("user_name");
        assertEquals("user_name", client.getUsername());
        server.stop();
    }
}
