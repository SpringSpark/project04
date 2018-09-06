package com.db.project04.client;

import com.db.project04.command.*;
import com.db.project04.exceptions.ChatParseCommandException;

import java.util.Scanner;

/**
 * Console class for client
 */
public class ClientMain {
    public static void main(String[] args) {
        try {
            Client client = new Client();
            while (true) {
                Scanner in = new Scanner(System.in);
                String inputString = in.nextLine();
                ChatCommand clientCommand = null;
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
                    //e.printStackTrace();
                }
                if (clientCommand instanceof HistoryCommand) {
                   client.receiveAndPrint();
                }

                if (clientCommand instanceof SendMessageCommand) {
                    client.receiveAndPrint();
                }
            }

        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }
}

