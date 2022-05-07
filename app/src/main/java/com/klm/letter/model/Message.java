package com.klm.letter.model;

public class Message {
    private String message;
    private String senderID;

    public Message() {
        message = null;
        senderID = null;
    }

    public Message(String message, String senderID)
    {
        this.message = message;
        this.senderID = senderID;
    }

    public String getMessage() {
        return message;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }
}
