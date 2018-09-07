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

/**
 * Client class implementation.
 * Connects to server, sends and receives messages.
 */

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

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }

    public Client(String username, Socket socket) throws ChatClientException, IOException {
        this.username = username;
        this.socket = socket;
        out = new PrintWriter(
                new OutputStreamWriter(
                        new BufferedOutputStream(
                                socket.getOutputStream())));
        in = new BufferedReader(
                new InputStreamReader(
                        new BufferedInputStream(
                                socket.getInputStream())));
    }


    public void send(String stringMessage) {
        out.println(stringMessage);
        out.flush();
    }

    /**
     * Receives messages from server and prints it to console.
     * @throws ChatClientException when is unable to handle message from server.
     */
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
