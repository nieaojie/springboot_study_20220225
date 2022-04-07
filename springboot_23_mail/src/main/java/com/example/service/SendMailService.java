package com.example.service;

import com.example.domain.Mail;

public interface SendMailService {
    void sendSimpleMail(Mail mail);

    void sendMimeMail(Mail mail);
}
