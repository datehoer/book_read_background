package com.datehoer.bookapi.service;

public interface MailService {
    boolean sendSimpleMail(String to, String subject, String content);
}
