import com.db.project04.client.Client;
import com.db.project04.exceptions.ChatClientException;
import com.db.project04.server.Server;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ClientTest {
    @Test
    public void clientSetUserName() throws IOException, ChatClientException {
        Server server = mock(Server.class);
        server.start();
        Socket mockSocket = mock(Socket.class);
        Client client = new Client("user_name", mockSocket);
        assertEquals("user_name", client.getUsername());
        server.stop();
    }

    @Test
    public void clientShouldSend() throws IOException, ChatClientException {
        Server server = mock(Server.class);
        server.start();
        Socket mockSocket = mock(Socket.class);
        Client client = new Client("user_name", mockSocket);

        PrintWriter mockOut = mock(PrintWriter.class);
        client.setOut(mockOut);
        client.send("test message");
        verify(mockOut, times(1)).println("test message");
    }

    @Test (expected = ChatClientException.class)
    public void clientShouldNotReceiveAndPrintWithoutInput() throws IOException, ChatClientException {
        Server server = mock(Server.class);
        server.start();
        Socket mockSocket = mock(Socket.class);
        Client client = new Client("user_name", mockSocket);
        client.receiveAndPrint();
    }

    @Test
    public void clientShouldReceiveAndPrint() throws IOException, ChatClientException {
        Server server = mock(Server.class);
        server.start();
        Socket mockSocket = mock(Socket.class);
        Client client = new Client("user_name", mockSocket);
        BufferedReader mockIn = mock(BufferedReader.class);
        client.setIn(mockIn);

        client.receiveAndPrint();
        verify(mockIn, times(1)).readLine();
    }

    @Test
    public void clientShouldQuit() throws IOException, ChatClientException {
        Server server = mock(Server.class);
        server.start();
        Socket mockSocket = mock(Socket.class);
        Client client = new Client("user_name", mockSocket);
        BufferedReader mockIn = mock(BufferedReader.class);
        client.setIn(mockIn);
        PrintWriter mockOut = mock(PrintWriter.class);
        client.setOut(mockOut);

        client.quit();
        verify(mockIn, times(1)).close();
        verify(mockSocket, times(1)).close();
        verify(mockOut, times(1)).close();
    }
}
