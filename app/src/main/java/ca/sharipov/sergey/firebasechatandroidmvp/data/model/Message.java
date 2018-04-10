package ca.sharipov.sergey.firebasechatandroidmvp.data.model;

import java.util.Date;

public class Message {
    private String senderId;
    private String content;
    private long date;

    public Message() {
    }

    public Message(String senderId, String content) {
        this.senderId = senderId;
        this.content = content;
        this.date = new Date().getTime();
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
