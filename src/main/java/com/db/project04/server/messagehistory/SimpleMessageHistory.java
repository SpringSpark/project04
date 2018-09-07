package com.db.project04.server.messagehistory;

import com.db.project04.exceptions.FileException;
import com.db.project04.message.ServerMessage;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SimpleMessageHistory extends MessageHistory {
    private FileSaver fileSaver;
    protected List<ServerMessage> bufferHistory;

    public SimpleMessageHistory() throws FileException {
        messageHistoryArray = new CopyOnWriteArrayList<>();
        bufferHistory = new CopyOnWriteArrayList<>();
        fileSaver = new FileSaver("utf-8");
    }

    @Override
    public List<ServerMessage> getMessageHistory() {
        return messageHistoryArray;
    }

    @Override
    public void addNewMessage(ServerMessage serverMessage) {
        messageHistoryArray.add(serverMessage);
        bufferHistory.add(serverMessage);
    }

    @Override
    public void saveToFile() throws FileException {
        Iterator<ServerMessage> iterator = bufferHistory.iterator();
        while(iterator.hasNext()){
            ServerMessage serverMessage = iterator.next();
            fileSaver.save(serverMessage.toString());
        }
        fileSaver.getPrintWriter().flush();
        bufferHistory.clear();
    }

}
