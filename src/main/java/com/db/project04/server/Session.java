package com.db.project04.server;

import com.db.project04.command.*;
import com.db.project04.exceptions.ChatException;
import com.db.project04.exceptions.ChatParseCommandException;
import com.db.project04.message.ServerMessage;
import com.db.project04.server.messagehistory.MessageHistory;
import com.db.project04.server.messagehistory.SimpleMessageHistory;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

public class Session implements Runnable {

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    static Collection<PrintWriter> clientPool = new ArrayList();
    private MessageHistory messageHistory = new SimpleMessageHistory();
    String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());

    public Session(Socket client) {
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
                            out.println("end");
                            out.flush();
                        }
                        if (command instanceof SendMessageCommand) {


                            String message = ((SendMessageCommand) command).getHandledString();
                            String messageToClient = date + " " + message;
                            messageHistory.addNewMessage(new ServerMessage(((SendMessageCommand) command).getHandledString(), date));
                            for (PrintWriter out : clientPool) {
                                out.println(messageToClient);
                                out.println("end");
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
                e.printStackTrace();
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