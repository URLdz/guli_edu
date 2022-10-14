package com.url.edustatistics.client;

import com.url.commonutils.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xidazhen
 * @date 2022/10/11 - 10:14
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    @GetMapping("/educenter/member/countRegister/{day}")
    public CommonResult countRegister(@PathVariable("day") String day);
}
