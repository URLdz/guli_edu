package com.url.msmservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.url.msmservice.service.MsmService;
import com.url.msmservice.utils.ConstantPropertiesUtils;
import com.url.msmservice.utils.RandomUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xidazhen
 * @date 2022/10/4 - 20:47
 */
@Service
public class MsmServiceImpl implements MsmService {
    @Override
    public Boolean sendMsm(String phone,Map<String,Object>  param) {

        if (StringUtils.isEmpty(phone))
            return false;


        DefaultProfile profile = DefaultProfile.getProfile("default", ConstantPropertiesUtils.KEY_ID, ConstantPropertiesUtils.KEY_SECRET);

        IAcsClient client = new DefaultAcsClient(profile);

        //设置相关固定的参数
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        //设置发送相关的参数
        request.putQueryParameter("PhoneNumbers",phone); //手机号
        request.putQueryParameter("SignName","阿里云短信测试"); //申请阿里云 签名名称
        request.putQueryParameter("TemplateCode","SMS_154950909"); //申请阿里云 模板code
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param)); //验证码数据，转换json数据传递

        try {
            CommonResponse response = client.getCommonResponse(request);
            boolean success = response.getHttpResponse().isSuccess();
            return success;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
