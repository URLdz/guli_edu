package com.url.eduservice.client;

import com.url.commonutils.CommonResult;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xidazhen
 * @date 2022/10/1 - 19:09
 */

/**
 * 接口方法出错兜底方法
 */
@Component
public class VodFileDegradeFeignClient implements VodClient{
    @Override
    public CommonResult deleteAlyVideo(String id) {
        return CommonResult.error().message("删除视频出错");
    }

    @Override
    public CommonResult deleteBatch(List<String> videoList) {
        return CommonResult.error().message("删除多个视频出错");
    }
}
