package com.db.project04.server;

import java.io.IOException;

public class ServerMain {

    public static void main(String[] args) {
        try {
            Server serverMain = new Server();
            serverMain.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
