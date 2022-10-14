package com.url.msmservice.controller;

import com.url.commonutils.CommonResult;
import com.url.msmservice.service.MsmService;
import com.url.msmservice.utils.RandomUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author xidazhen
 * @date 2022/10/4 - 20:46
 */
@RestController

@Api(description = "短信验证控制器")
@RequestMapping("/edumsm/msm")
public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @ApiOperation("短信发送服务")
    @GetMapping("send/{phone}")
    public CommonResult sendMsm(@ApiParam(name = "phone",value = "手机号码") @PathVariable String phone){
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)){
            System.out.println(code);
            return CommonResult.ok();
        }

        code = RandomUtil.getFourBitRandom();
        System.out.println(code);
        Map<String,Object> param = new HashMap<>();
        param.put("code",code);

        Boolean isSend = msmService.sendMsm(phone,param);
        if (isSend) {
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return CommonResult.ok();
        }
        else {
            return CommonResult.error().message("短信发送失败");
        }
    }
}
