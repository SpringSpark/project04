package com.db.project04.message;

import java.util.Date;

public class ServerMessage {
    private String text;
    private Date dateTime;

  public ServerMessage(String clientMessage) {
      //TODO: get time
      //TODO: get text message
  }

    public void setText(String text) {
        this.text = text;
    }

}
