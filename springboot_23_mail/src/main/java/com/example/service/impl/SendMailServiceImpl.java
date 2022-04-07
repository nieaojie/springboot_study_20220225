package com.example.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.domain.Mail;
import com.example.service.SendMailService;

@Service
public class SendMailServiceImpl implements SendMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendSimpleMail(Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mail.getFrom());
        message.setTo(mail.getTo());
        message.setSubject(mail.getSubject());
        message.setText(mail.getText());
        javaMailSender.send(message);
    }

    @Override
    public void sendMimeMail(Mail mail) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            //这个构造方法可以设置是否包含附件
            MimeMessageHelper helper = new MimeMessageHelper(message, mail.isContainsAttachment());
            //发送人
            helper.setFrom(mail.getFrom());
            //接收人
            helper.setTo(mail.getTo());
            //邮件主题
            helper.setSubject(mail.getSubject());
            //邮件内容-是否解析HTML文本（默认false）
            helper.setText(mail.getText(), mail.isHtml());
            if (mail.isContainsAttachment()) {
                //添加附件
                helper.addAttachment(mail.getAttachment().getName(), mail.getAttachment());
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(message);
    }
}
