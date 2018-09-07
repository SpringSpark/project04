package com.db.project04.server;

import com.db.project04.RemoteConfiguration;
import com.db.project04.exceptions.FileException;
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
    public  MessageHistory messageHistory;
    ServerSocket portListener;

    public Server() throws IOException {
        try {
            messageHistory = new SimpleMessageHistory();
        } catch (FileException e) {
            System.out.println("Can't save history to file");
        }
        portListener  = new ServerSocket(RemoteConfiguration.PORT_NUMBER);
    }

    public void start() throws IOException {


        new Thread(() -> {
                while (!interrupted()) {
                    try {
                        Socket clientSession = portListener.accept();
                        Session.clientPool.add(new PrintWriter(
                                new OutputStreamWriter(
                                        new BufferedOutputStream(
                                                clientSession.getOutputStream()))));
                        pool.execute(new Session(clientSession, messageHistory));
                    }
                    catch(IOException e){
                        System.out.println("Client is not ok");
                    }

                }
        }).start();
        System.out.println("We are alive");
    }

    public void stop() throws IOException {
        pool.shutdownNow();
        portListener.close();
    }
}

