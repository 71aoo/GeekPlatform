package com.syclover.geekPlatform.service.impl;

import com.syclover.geekPlatform.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author cueyu
 * @Date 2020/9/4
 */

@Component
@Service
public class MailServiceImpl implements MailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;


    @Async("taskExecutor")
    @Override
    public void sendSimpleMail(String receiver, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(receiver);
        message.setText(content);
        message.setSubject(subject);

        try{
            mailSender.send(message);
            logger.info("邮件已发送");
        }catch (Exception e){
            logger.error("发送邮件出错");
        }
    }
}
