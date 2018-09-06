package com.db.project04.server.messagehistory;

import com.db.project04.message.ServerMessage;

import java.util.ArrayList;
import java.util.List;

public class SimpleMessageHistory extends MessageHistory {
    @Override
    public List<ServerMessage> getMessageHistory() {
        return messageHistoryArray;
    }

    @Override
    public void addNewMessage(ServerMessage serverMessage) {
        messageHistoryArray.add(serverMessage);
    }

    public SimpleMessageHistory(){
        messageHistoryArray = new ArrayList<ServerMessage>();
    }



}
