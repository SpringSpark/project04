package com.db.project04.server;

import java.io.IOException;

public class ServerMain {

    public static void main(String[] args) {
        Server serverMain = new Server();
        try {
            serverMain.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
