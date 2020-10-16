package com.syclover.geekPlatform.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.syclover.geekPlatform.common.ResponseCode;
import com.syclover.geekPlatform.common.ResultT;
import io.netty.handler.ssl.ReferenceCountedOpenSslClientContext;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Author: Playwi0
 * @Data: 2020/10/16
 */
public class AliyunOSSUtils {


    private static final String endpoint = "oss-cn-beijing.aliyuncs.com";

    private static final String accessKeyId = "LTAI4G9h6yUF1BH8NTRzg4Sa";

    private static final String accessKeySecret = "ZiiAb64xm2ZcdPdPOBPt7F5BzWVQ81";

    private static final String bucketName = "geekplateform";

    private static final String URL = "https://geekplateform.oss-cn-beijing.aliyuncs.com";

//    private static OSS ossClient = null;
//
//
//    private static OSS getOSSClient(){
//
//        if (ossClient == null){
//            synchronized (AliyunOSSUtils.class){
//                if (ossClient == null){
//                    ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//                }
//            }
//        }
//
//        return ossClient;
//    }

    public static ResultT<String> uploadFile(InputStream is, String suffix){

        String fileName = "";

        if (is == null){
            return new ResultT<>(ResponseCode.ERROR.getCode(),"文件为空", null);
        }

        if (suffix == null || suffix.equals("")){
            fileName = UUID.randomUUID().toString().replaceAll("-", "") + ".png";
        }else {
            fileName = UUID.randomUUID().toString().replaceAll("-", "") + suffix;
        }


        try {

            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            ossClient.putObject(bucketName, fileName, is);
            ossClient.shutdown();

        }catch (Exception e){
            return new ResultT<String>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMsg(), null);
        }


        return new ResultT<String>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(), URL + '/' + fileName);


    }


}
