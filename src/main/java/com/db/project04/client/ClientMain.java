package com.db.project04.client;
import com.db.project04.command.ChatCommand;
import com.db.project04.command.ChidCommand;
import com.db.project04.command.ClientCommandController;
import com.db.project04.exceptions.ChatClientException;
import com.db.project04.exceptions.ChatParseCommandException;
import java.util.Scanner;

/**
 * Console class for client
 */
public class ClientMain {
    public static void main(String[] args) {
        try {
            Client client = new Client();
            Thread newThread = new Thread( () -> {
                try {
                    client.receiveAndPrint();
                } catch (ChatClientException e) {
                    System.out.println("Server is dead");
                    ShutdownClient(client);
                }
            }
            );
            newThread.start();
            while (true) {

                newThread.join(10);
                Scanner in = new Scanner(System.in);
                String inputString = in.nextLine();
                setUsernameOrSendMessage(client, inputString);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setUsernameOrSendMessage(Client client, String inputString) {
        ChatCommand clientCommand;
        try {
            clientCommand = ClientCommandController.parseCommand(inputString, client.getUsername());
            if(clientCommand instanceof ChidCommand){
                client.setUsername(((ChidCommand) clientCommand).getHandledString());
            }
            else{
                client.send(client.getUsername() + " " + inputString);
            }
        } catch (ChatParseCommandException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void ShutdownClient(Client client) {
        try {
            client.quit();
        } catch (ChatClientException e1) {
            System.out.println("Can't shutdown client in a proper way");
        }
        finally {
            System.exit(0);
        }
    }

    private ClientMain(){}
}

