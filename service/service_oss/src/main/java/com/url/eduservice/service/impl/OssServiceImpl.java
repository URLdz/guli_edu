package com.url.eduservice.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.url.eduservice.service.OssService;
import com.url.eduservice.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * @author xidazhen
 * @date 2022/9/21 - 14:11
 */
@Service
public class OssServiceImpl implements OssService {
    //上传头像到oss
    public String uploadFileAvatar(MultipartFile file) {

        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            //获取上传文件输入流
            InputStream inputStream = file.getInputStream();
            // 创建PutObject请求。
            String fileName = file.getOriginalFilename();
            //添加随机值 添加按日期分类文件夹
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            String datePath = new DateTime().toString("yyyy/MM/dd");
            fileName = datePath + "/" + uuid + fileName;

            ossClient.putObject(bucketName, fileName, inputStream);
            ossClient.shutdown();

            String url = "https://"+bucketName+"."+endpoint+"/"+fileName;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
