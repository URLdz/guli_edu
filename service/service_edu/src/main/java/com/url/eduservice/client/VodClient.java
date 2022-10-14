package com.url.eduservice.client;

import com.url.commonutils.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * @author xidazhen
 * @date 2022/9/30 - 20:21
 */
@Component
@FeignClient(value = "service-vod",fallback = VodFileDegradeFeignClient.class)
public interface VodClient {
    @DeleteMapping("/eduvod/video/deleteAlyVideo/{id}")
    public CommonResult deleteAlyVideo(@PathVariable("id") String id); //@PathVariable 必须写明参数名称

    @DeleteMapping("/eduvod/video/deleteBatch")
    public CommonResult deleteBatch(@RequestParam("videoList") List<String> videoList);
}