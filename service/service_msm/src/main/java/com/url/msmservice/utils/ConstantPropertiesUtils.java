package com.url.msmservice.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author xidazhen
 * @date 2022/9/21 - 13:39
 */
@Component
public class ConstantPropertiesUtils implements InitializingBean {

    @Value("${aliyun.file.keyid}")
    private String keyId;

    @Value("${aliyun.file.keysecret}")
    private String keySecret;

    public static String KEY_ID;
    public static String KEY_SECRET;
    @Override
    public void afterPropertiesSet() throws Exception {
        KEY_ID = keyId;
        KEY_SECRET = keySecret;
    }
}
