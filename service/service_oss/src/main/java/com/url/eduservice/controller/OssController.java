package com.url.eduservice.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.url.commonutils.CommonResult;
import com.url.eduservice.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xidazhen
 * @date 2022/9/21 - 13:51
 */
@Api(description = "文件上传-oss")
@RestController
@RequestMapping("/eduoss/fileoss")

public class OssController {
    @Autowired
    private OssService ossService;

    @ApiOperation(value = "文件上传-oss")
    @PostMapping
    public CommonResult uploadOssFile(@ApiParam(name = "file",value = "文件") MultipartFile file){
        String url = ossService.uploadFileAvatar(file);
        return CommonResult.ok().data("url",url);
    }
}
