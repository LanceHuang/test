package com.lance.test.event;

/**
 * @author Lance
 * @since 2018-10-27 16:05:50
 */
public class ReceiveMessageEvent implements IEvent {

    private String sender;
    private String message;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
