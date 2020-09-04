package com.syclover.geekPlatform.serviceTest;

import com.syclover.geekPlatform.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author cueyu
 * @Date 2020/9/4
 */
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Test
    public void testSimpleMail(){
        String content = "test Email content:http://www.cueyu.com";
        mailService.sendSimpleMail("cueyu@foxmail.com","test simple mail",content);
    }


}
