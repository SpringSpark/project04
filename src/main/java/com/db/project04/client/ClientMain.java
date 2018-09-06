package com.db.project04.client;

import com.db.project04.command.ChatCommand;
import com.db.project04.command.CommandController;
import com.db.project04.command.HistoryCommand;
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
            while (true) {
                Scanner in = new Scanner(System.in);
                String inputString = in.nextLine();
                ChatCommand clientCommand = null;
                try {
                    clientCommand = CommandController.parseCommand(inputString);
                } catch (ChatParseCommandException e) {
                    e.printStackTrace();
                }
                client.send(inputString);
                if (clientCommand instanceof HistoryCommand) {
                    System.out.println(client.receive());
                }
            }

        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }
}

