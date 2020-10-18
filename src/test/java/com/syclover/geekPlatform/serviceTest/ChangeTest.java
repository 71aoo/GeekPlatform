package com.syclover.geekPlatform.serviceTest;

import com.syclover.geekPlatform.util.BCPEUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author cueyu
 * @Date 2020/10/17
 */

@SpringBootTest
public class ChangeTest {

    @Test
    public void change(){
        String rawPass = "ottootto";
        System.out.println(BCPEUtils.encode(rawPass));

    }

}
