package com.db.project04.server;

import com.db.project04.command.ChatCommand;
import com.db.project04.command.HistoryCommand;
import com.db.project04.command.SendMessageCommand;
import com.db.project04.command.ClientShutdownCommand;
import com.db.project04.command.ServerCommandController;
import com.db.project04.exceptions.ChatException;
import com.db.project04.exceptions.ChatParseCommandException;
import com.db.project04.message.ServerMessage;
import com.db.project04.server.messagehistory.MessageHistory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class Session implements Runnable {

    private final Socket client;
    private BufferedReader in;
    private PrintWriter out;
    static Collection<PrintWriter> clientPool = new CopyOnWriteArrayList();
    String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
    private MessageHistory messageHistory;

    public Socket getClient(){return client;}

    public MessageHistory getMessageHistory(){return messageHistory;}


    public Session(Socket client, MessageHistory messageHistory) {
        this.client = client;
        try {
            this.out = new PrintWriter(
                    new OutputStreamWriter(
                            new BufferedOutputStream(
                                    client.getOutputStream())));
            this.in = new BufferedReader(
                    new InputStreamReader(
                            new BufferedInputStream(
                                    client.getInputStream())));
            this.messageHistory = messageHistory;
            System.out.println("New connection");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            String line = null;
            try {
                while ((line = in.readLine()) != null) {
                    parseInputLine(line);
                    out.println();
                    out.flush();
                }

            } catch (IOException | ChatException e) {
                Thread.currentThread().interrupt();
            }
        }
        try {
            in.close();
            out.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void parseInputLine(String line) throws ChatException {
        try {
            ChatCommand command = ServerCommandController.parseCommand(line);
            if (command instanceof HistoryCommand) {
                handleHistoryCommand();
            }
            if (command instanceof SendMessageCommand) {
                handleSendMessageCommand((SendMessageCommand) command);
            }
        } catch (ChatParseCommandException e) {
            out.println("Something went wrong, retry, please");
        }
    }

    private void handleHistoryCommand() {
        messageHistory.getMessageHistory().stream().forEach(elem -> {
            out.println(elem.toString());
            out.flush();
        });
    }

    private void handleSendMessageCommand(SendMessageCommand command) {
        String message = command.getHandledString();
        String messageToClient = date + " " + message;
        messageHistory.addNewMessage(new ServerMessage(command.getHandledString(), date));

        Iterator<PrintWriter> iterator = clientPool.iterator();
        while(iterator.hasNext()){
            PrintWriter out = iterator.next();
            out.println(messageToClient);
            out.flush();
        }
    }
}