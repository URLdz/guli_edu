package com.url.eduservice.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.url.commonutils.CommonResult;
import com.url.eduservice.service.VodService;
import com.url.eduservice.utils.ConstantVodUtils;
import com.url.eduservice.utils.InitVodClient;
import com.url.servicebase.exceptionhandle.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author xidazhen
 * @date 2022/9/29 - 13:39
 */
@Api(description = "视频点播")

@RestController
@RequestMapping("/eduvod/video")
public class VodController {

    @Autowired
    private VodService vodService;

    @ApiOperation("上传视频")
    @PostMapping("uploadAlyVideo")
    public CommonResult uploadAlyVideo(@ApiParam(name = "file", value = "视频文件") MultipartFile file) {
        String videoId = vodService.uploadAlyVideo(file);
        return CommonResult.ok().data("id", videoId);
    }

    @ApiOperation("删除课时视频")
    @DeleteMapping("deleteAlyVideo/{id}")
    public CommonResult deleteAlyVideo(@ApiParam(name = "id", value = "视频id") @PathVariable String id) {
        Boolean flag = vodService.deleteAlyVideo(id);
        if (flag) {
            return CommonResult.ok();
        } else {
            throw new GuliException(20001, "删除视频失败");
        }
    }

    @ApiOperation("删除多个视频")
    @DeleteMapping("deleteBatch")
    public CommonResult deleteBatch(@RequestParam("videoList") List<String> videoList) {
        Boolean flag = vodService.deleteBatch(videoList);
        if (flag) {
            return CommonResult.ok();
        } else {
            throw new GuliException(20001, "删除视频失败");
        }
    }

    @ApiOperation("获取视频凭证")
    @GetMapping("getPlayAuth/{id}")
    public CommonResult getPlayAuth(@ApiParam(name = "id",value = "视频id")@PathVariable String id){
        try {
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(id);
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            String playAuth = response.getPlayAuth();
            return CommonResult.ok().data("playAuth",playAuth);
        }catch (Exception e){
            throw new GuliException(20001,"获取凭证失败");
        }
    }
}
