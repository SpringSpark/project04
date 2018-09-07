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

public class Session implements Runnable {

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    static Collection<PrintWriter> clientPool = new ArrayList();
    String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
    private MessageHistory messageHistory;

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
                    try {
                        ChatCommand command = ServerCommandController.parseCommand(line);
                        if (command instanceof HistoryCommand) {

                            messageHistory.getMessageHistory().stream().forEach(elem -> {
                                out.println(elem.toString());
                                out.flush();
                            });
                        }
                        if (command instanceof SendMessageCommand) {
                            String message = ((SendMessageCommand) command).getHandledString();
                            String messageToClient = date + " " + message;
                            messageHistory.addNewMessage(new ServerMessage(((SendMessageCommand) command).getHandledString(), date));
                            for (PrintWriter out : clientPool) {
                                out.println(messageToClient);
                                out.flush();
                            }
                            if (command instanceof ClientShutdownCommand) {
                                clientPool.remove(this.out);
                            }
                        }
                    } catch (ChatParseCommandException e) {
                        out.println("Something went wrong, retry, please");
                    }
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
}