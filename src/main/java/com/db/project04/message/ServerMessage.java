package com.db.project04.message;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Class which represents Message as it is represented on Server.
 */
public class ServerMessage {
    private String text;
    private Date dateTime;

  public ServerMessage(String handledMessageFromClient) {
      this.text = handledMessageFromClient;
      //TODO: get text message
  }

    public void setText(String text) {
        this.text = text;
    }

}
