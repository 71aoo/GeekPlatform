package com.syclover.geekPlatform.oss;

import com.syclover.geekPlatform.common.ResultT;
import com.syclover.geekPlatform.util.AliyunOSSUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @Author: Playwi0
 * @Data: 2020/10/16
 */
@SpringBootTest
public class AliyunOSSTest {

    @Test
    public void uploadTest() throws FileNotFoundException {
        File file = new File("C:\\Users\\Playwi0\\Desktop\\QQ图片20201014224242.jpg");
        FileInputStream inputStream = new FileInputStream(file);
        String fileName = "testest";
        ResultT<String> stringResultT = AliyunOSSUtils.uploadFile(inputStream, null);
        System.out.println(stringResultT);
    }
}
