package com.globallogic.refactoredpractice.model;

public class Email {
    private String recipient;
    private String body;
    private String subject;
    private String attachment;

    // No args constructor
    public Email() {}

    // All args constructor
    public Email(String recipient, String body, String subject, String attachment) {
        this.recipient = recipient;
        this.body = body;
        this.subject = subject;
        this.attachment = attachment;
    }

    // Getters and setters
    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
