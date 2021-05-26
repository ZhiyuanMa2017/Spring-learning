package com.squirrel.mail.result;

public class MailResult {

    private String respondCode;
    private String respondMessage;

    public MailResult() {
        this.respondCode = "00";
        this.respondMessage = "Success";
    }

    public String getRespondCode() {
        return respondCode;
    }

    public void setRespondCode(String respondCode) {
        this.respondCode = respondCode;
    }

    public String getRespondMessage() {
        return respondMessage;
    }

    public void setRespondMessage(String respondMessage) {
        this.respondMessage = respondMessage;
    }
}
