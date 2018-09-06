package com.db.project04.server;

import com.db.project04.RemoteConfiguration;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

import static java.lang.Thread.interrupted;
import static java.util.concurrent.Executors.newFixedThreadPool;

public class ServerMain {
    public static void main(String[] args) {
        ServerMain serverMain = new ServerMain();
        try {
            serverMain.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    ExecutorService pool = newFixedThreadPool(RemoteConfiguration.POOL_SIZE);

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
                    pool.execute(new Session(clientSession));
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

