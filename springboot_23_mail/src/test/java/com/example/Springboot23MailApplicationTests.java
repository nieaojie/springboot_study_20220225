package com.example;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.domain.Mail;
import com.example.service.SendMailService;

@SpringBootTest
class Springboot23MailApplicationTests {

    @Autowired
    private SendMailService sendMailService;

    @Test
    void testSendSimpleMail() {
        Mail mail = new Mail();
        //mail.setFrom("xxxxxx@qq.com");
        //会替换这个邮箱名称，改用括号里面的名称。
        mail.setFrom("xxxxxx@qq.com" + "(小甜甜)");
        mail.setTo("xxxxxx@163.com");
        mail.setSubject("测试邮件发送");
        mail.setText("这是正文内容...");
        sendMailService.sendSimpleMail(mail);
    }

    @Test
    void testSendMimeMail() {
        Mail mail = new Mail();
        mail.setFrom("xxxxxx@qq.com" + "(小甜甜)");
        mail.setTo("xxxxxx@163.com");
        mail.setSubject("测试邮件发送");
        mail.setText(
                "<img src='https://pics4.baidu.com/feed/472309f790529822d27838e192935ec10a46d428.png?token=80be45e12623a1b3999ed96cd882f289'/>"
                + "<a href='https://www.baidu.com/'>百度一下</a>");
        mail.setHtml(true);
        sendMailService.sendMimeMail(mail);
    }

    /**
     * 添加附件
     */
    @Test
    void testSendMimeMailContainsFile() {
        Mail mail = new Mail();
        mail.setFrom("xxxxxx@qq.com" + "(小甜甜)");
        mail.setTo("xxxxxx@163.com");
        mail.setSubject("测试邮件发送");
        mail.setText(
                "<img src='https://pics4.baidu.com/feed/472309f790529822d27838e192935ec10a46d428.png?token=80be45e12623a1b3999ed96cd882f289'/>"
                + "<a href='https://www.baidu.com/'>百度一下</a>");
        mail.setHtml(true);
        mail.setContainsAttachment(true);
        mail.setAttachment(new File(
                "F:\\Study\\IdeaProjects\\springboot_study_20220225\\springboot_23_mail\\src\\main\\java\\com\\example\\service\\impl\\SendMailServiceImpl.java"));
        sendMailService.sendMimeMail(mail);
    }
}
