package com.db.project04.server;

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

    public static void main(String[] args) throws IOException {
        ServerSocket portListener = new ServerSocket(6666);
        ExecutorService pool = newFixedThreadPool(10_000);
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
}

