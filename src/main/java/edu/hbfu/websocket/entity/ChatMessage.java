package edu.hbfu.websocket.entity;

public class ChatMessage {
    private String from;
    private String content;
    private long timestamp;
    public ChatMessage() {}
    public ChatMessage(String from, String content, long timestamp) {
        this.from = from;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }







}
