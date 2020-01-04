package com.movify.service;

public interface EmailService {

    public void sendEmail(String to, String toName, String subject, String content);

}
