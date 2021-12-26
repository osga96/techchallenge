package com.example.demo.mail;

import com.sun.mail.util.MailConnectException;

public interface EmailService {
    public void sendSimpleMessage(String to, String subject, String text) throws MailConnectException;
}
