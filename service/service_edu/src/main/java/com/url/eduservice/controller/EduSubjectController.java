package com.url.eduservice.controller;


import com.url.commonutils.CommonResult;
import com.url.eduservice.entity.subject.OneSubject;
import com.url.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author xidazhen
 * @since 2022-09-22
 */
@Api(description = "课程分类管理")
@RestController
@RequestMapping("/eduservice/subject")

public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

    //添加课程分类
    //获取上传文件并读取
    @ApiOperation("增加课程分类")
    @PostMapping("addSubject")
    public CommonResult addSubject(@ApiParam(name = "file",value = "xls格式文件") MultipartFile file) {
        eduSubjectService.saveSubject(file, eduSubjectService);
        return CommonResult.ok();
    }

    @ApiOperation("获取所有课程分类")
    @GetMapping("getAllSubject")
    public CommonResult getAllSubject(){
        List<OneSubject> list = eduSubjectService.getAllOneTwoSubject();
        return CommonResult.ok().data("list",list);
    }
}

