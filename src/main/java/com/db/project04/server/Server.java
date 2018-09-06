package com.db.project04.server;

import com.db.project04.RemoteConfiguration;
import com.db.project04.server.messagehistory.MessageHistory;
import com.db.project04.server.messagehistory.SimpleMessageHistory;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

import static java.lang.Thread.interrupted;
import static java.util.concurrent.Executors.newFixedThreadPool;

public class Server {

    ExecutorService pool = newFixedThreadPool(RemoteConfiguration.POOL_SIZE);
    public  MessageHistory messageHistory  =new SimpleMessageHistory();

    public Server() {
        messageHistory = new SimpleMessageHistory();
    }

    public void start() throws IOException {
        ServerSocket portListener = new ServerSocket(RemoteConfiguration.PORT_NUMBER);

        new Thread(() -> {
            try {
                while (!interrupted()) {
                    Socket clientSession = portListener.accept();
                    Session.clientPool.add(new PrintWriter(
                            new OutputStreamWriter(
                                    new BufferedOutputStream(
                                            clientSession.getOutputStream()))));
                    pool.execute(new Session(clientSession, messageHistory));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println("We are alive");
    }

    public void stop() throws IOException {
        pool.shutdownNow();
    }
}

