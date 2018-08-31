package com.db.project04.server.messagehistory;

import com.db.project04.message.ServerMessage;

import java.util.ArrayList;

public abstract class MessageHistory {
    protected ArrayList<ServerMessage> messageHistoryArray;
    public abstract ArrayList<ServerMessage> getMessageHistory();
    public abstract void addNewMessage(ServerMessage serverMessage);
}
