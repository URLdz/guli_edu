package com.url.eduservice.controller;


import com.url.commonutils.CommonResult;
import com.url.eduservice.entity.EduCourse;
import com.url.eduservice.entity.vo.CourseInfoVo;
import com.url.eduservice.entity.vo.CoursePublishVo;
import com.url.eduservice.entity.vo.CourseQueryVo;
import com.url.eduservice.service.EduCourseService;
import com.url.servicebase.exceptionhandle.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author xidazhen
 * @since 2022-09-23
 */
@RestController
@RequestMapping("/eduservice/course")
@Api(description = "课程管理")

public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    @ApiOperation("增加课程介绍")
    @PostMapping("addCourseInfo")
    public CommonResult addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String id = eduCourseService.saveCourseInfo(courseInfoVo);
        return CommonResult.ok().data("courseId",id);
    }

    //根据课程查询课程基本信息
    @ApiOperation("查询课程基本信息")
    @GetMapping("getCourseInfo/{courseId}")
    public CommonResult getCourseInfo(@ApiParam(name = "courseId", value = "课程ID", required = true) @PathVariable String courseId){
        CourseInfoVo courseInfoVo = eduCourseService.getCourseInfo(courseId);
        return CommonResult.ok().data("courseInfoVo",courseInfoVo);
    }

    @ApiOperation("修改课程基本信息")
    @PostMapping("updateCourseInfo")
    public CommonResult updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        eduCourseService.updateCourseInfo(courseInfoVo);
        return CommonResult.ok();
    }

    @ApiOperation("课程发布预览信息展示")
    @GetMapping("getPublishCourseInfo/{id}")
    public CommonResult getPublishCourseInfo(@ApiParam(name = "id", value = "课程ID", required = true) @PathVariable String id){
        CoursePublishVo coursePublishVo = eduCourseService.getPublishCourseInfo(id);
        return CommonResult.ok().data("coursePublishVo",coursePublishVo);
    }

    @ApiOperation("课程发布")
    @PostMapping("publishCourse/{id}")
    public CommonResult publishCourse(@ApiParam(name = "id", value = "课程ID", required = true) @PathVariable String id){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        boolean b = eduCourseService.updateById(eduCourse);
        if (b){
            return CommonResult.ok();
        }else {
            new GuliException(20001,"修改失败");
            return CommonResult.error();
        }
    }

    @ApiOperation("课程列表")
    @GetMapping("getCourseList")
    public CommonResult getCourseList(){
        List<EduCourse> list = eduCourseService.list(null);
        return CommonResult.ok().data("list",list);
    }

    @ApiOperation("课程分页列表")
    @GetMapping("getCourseListPage/{current}/{limit}")
    public CommonResult getCourseListPage(@ApiParam(name = "current", value = "当前页", required = true) @PathVariable long current,
                                          @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable long limit){
        Map map = eduCourseService.getCourseListPage(current,limit);
        return CommonResult.ok().data(map);
    }

    /**
     * @RequestBody ->Post请求
     * 将json数据封装到对应的对象里边
     */
    @ApiOperation(value = "条件分页查询课程")
    @PostMapping("pageCourseCondition/{current}/{limit}")
    public CommonResult pageCourseCondition(
            @ApiParam(name = "current", value = "当前页", required = true) @PathVariable long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable long limit,
            @RequestBody(required = false) CourseQueryVo courseQueryVo) {
        Map map = eduCourseService.pageCourseCondition(current, limit, courseQueryVo);
        return CommonResult.ok().data(map);
    }

    @ApiOperation(value = "删除课程所有内容")
    @DeleteMapping("{id}")
    public CommonResult deleteCourse(@ApiParam(name = "id", value = "课程ID", required = true) @PathVariable String id){
        eduCourseService.deleteCourse(id);
        return CommonResult.ok();
    }

}

