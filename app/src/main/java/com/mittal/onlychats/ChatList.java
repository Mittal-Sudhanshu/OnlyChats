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

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }
}
