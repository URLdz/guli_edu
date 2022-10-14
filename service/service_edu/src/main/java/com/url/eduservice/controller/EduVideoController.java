package com.url.eduservice.controller;


import com.url.commonutils.CommonResult;
import com.url.eduservice.client.VodClient;
import com.url.eduservice.entity.EduVideo;
import com.url.eduservice.service.EduVideoService;
import com.url.servicebase.exceptionhandle.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author xidazhen
 * @since 2022-09-23
 */
@Api(description = "小节视频管理")

@RestController
@RequestMapping("/eduservice/video")
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private VodClient vodClient;

    @ApiOperation("添加小节")
    @PostMapping("addVideo")
    public CommonResult addVideo(@RequestBody EduVideo eduVideo) {
        eduVideoService.save(eduVideo);
        return CommonResult.ok();
    }

    @ApiOperation("根据id查询小节")
    @GetMapping("getVideoInfo/{videoId}")
    public CommonResult getVideoInfo(@ApiParam(name = "videoId",value = "小节id",required = true) @PathVariable String videoId){
        EduVideo eduVideo = eduVideoService.getById(videoId);
        return CommonResult.ok().data("eduVideo",eduVideo);
    }

    @ApiOperation("更新小节")
    @PostMapping("updateVideo")
    public CommonResult updateVideo(@RequestBody EduVideo eduVideo) {
        eduVideoService.updateById(eduVideo);
        return CommonResult.ok();
    }

    @ApiOperation("删除小节")
    @DeleteMapping("{videoId}")
    public CommonResult deleteVideo(@ApiParam(name = "videoId",value = "小节id",required = true) @PathVariable String videoId) {
        EduVideo eduVideo = eduVideoService.getById(videoId);
        String videoSourceId = eduVideo.getVideoSourceId();
        if (!StringUtils.isEmpty(videoSourceId)){
            CommonResult commonResult = vodClient.deleteAlyVideo(videoSourceId);
            if (commonResult.getCode() == 20001){
                throw new GuliException(20001,"删除视频失败，熔断器生效");
            }
        }
        boolean i = eduVideoService.deleteVideo(videoId);
        return CommonResult.ok().data("i",i);
    }
}

