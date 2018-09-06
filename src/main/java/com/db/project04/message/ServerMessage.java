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

}
