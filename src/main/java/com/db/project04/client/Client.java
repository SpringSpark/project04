package com.db.project04.client;

import com.db.project04.RemoteConfiguration;
import com.db.project04.exceptions.ChatClientException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Client() throws ChatClientException {
        try {
            socket = new Socket(RemoteConfiguration.HOST, RemoteConfiguration.PORT_NUMBER);

            username = null;

            out = new PrintWriter(
                    new OutputStreamWriter(
                            new BufferedOutputStream(
                                    socket.getOutputStream())));
            in = new BufferedReader(
                    new InputStreamReader(
                            new BufferedInputStream(
                                    socket.getInputStream())));
        } catch (IOException e) {
            throw new ChatClientException("Unable to connect to server", e);
        }
    }

    public void send(String stringMessage) {
        out.println(stringMessage);
        out.flush();
    }

    public void receiveAndPrint() throws ChatClientException {
        String messageFromServer = null;
        try {
            while ((messageFromServer = in.readLine()) != null) {
                System.out.println(messageFromServer);
            }
        } catch (IOException e) {
            throw new ChatClientException("Unable to handle message server", e);
        }
    }

    public void quit() throws ChatClientException {
        try {
            socket.close();
            out.close();
            in.close();
        } catch (IOException e) {
            throw new ChatClientException("Unable to shutdown", e);
        }

    }


}
