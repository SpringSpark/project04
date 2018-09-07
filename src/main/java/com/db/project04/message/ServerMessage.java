package com.db.project04.message;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Class which represents Message as it is represented on Server.
 */
public class ServerMessage {
    private String text;
    private String dateTime;


  public ServerMessage(String handledMessageFromClient, String dateTime) {
      this.text = handledMessageFromClient;
      this.dateTime = dateTime;
      //TODO: get date time
  }

    public void setText(String text) {
      this.text = text;
    }

    public String getText(){
        return this.text;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDateTime(){
        return this.dateTime;
    }

    @Override
    public String toString(){
      return this.dateTime + " " + this.text;
    }

}
