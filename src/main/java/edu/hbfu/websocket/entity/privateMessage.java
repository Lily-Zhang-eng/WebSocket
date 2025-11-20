package edu.hbfu.websocket.entity;

public class privateMessage {
    public String from;
    public String to;
    public String content;

    public privateMessage() {
    }
    public privateMessage(String from, String to, String content) {
        this.from = from;

        this.to = to;
        this.content = content;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
