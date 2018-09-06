package com.db.project04.server.messagehistory;

import com.db.project04.message.ServerMessage;

import java.util.ArrayList;
import java.util.List;

public abstract class MessageHistory {
    protected static List<ServerMessage> messageHistoryArray;

    public abstract List<ServerMessage> getMessageHistory();

    public abstract void addNewMessage(ServerMessage serverMessage);
}
