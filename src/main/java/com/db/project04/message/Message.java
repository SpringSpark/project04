package com.db.project04.message;

import java.util.Date;

public class Message {
    private String text;
    private Date dateTime;

    public Message(String text, Date dateTime) {
        this.text = text;
        this.dateTime = dateTime;
    }

    public void setText(String text) {
        this.text = text;
    }
}
