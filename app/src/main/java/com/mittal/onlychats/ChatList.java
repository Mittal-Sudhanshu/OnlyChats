package com.mittal.onlychats;

public class ChatList {

    String uId,message;
    Long timestamp;

    public ChatList(String uId, String message, Long timestamp) {
        this.uId = uId;
        this.message = message;
        this.timestamp = timestamp;
    }

    public ChatList(String uId, String message) {
        this.uId = uId;
        this.message = message;
    }
    public ChatList(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getuId() {
        return uId;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }
}
