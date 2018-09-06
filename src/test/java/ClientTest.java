import com.db.project04.client.Client;
import com.db.project04.exceptions.ChatClientException;
import org.junit.Test;

public class ClientTest {
    @Test (expected = ChatClientException.class)
    public void clientShouldExitWhenThereIsNoServer() throws ChatClientException {
        Client client = new Client();
    }
}
