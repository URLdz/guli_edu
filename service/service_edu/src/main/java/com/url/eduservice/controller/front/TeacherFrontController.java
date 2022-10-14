package com.url.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.url.commonutils.CommonResult;
import com.url.eduservice.entity.EduCourse;
import com.url.eduservice.entity.EduTeacher;
import com.url.eduservice.service.EduCourseService;
import com.url.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author xidazhen
 * @date 2022/10/7 - 22:04
 */
@Api(description = "前台名师板块")
@RestController

@RequestMapping("/eduservice/teacherfront")
public class TeacherFrontController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @Autowired
    private EduCourseService eduCourseService;

    @ApiOperation("分页查询名师")
    @GetMapping("getTeacherFront/{current}/{limit}")
    public CommonResult getTeacherFront(@ApiParam(name = "current", value = "当前页", required = true) @PathVariable long current,
                                        @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable long limit) {
        Map<String,Object> map = eduTeacherService.getTeacherFrontList(current, limit);
        return CommonResult.ok().data(map);
    }

    @ApiOperation("教师详细信息")
    @GetMapping("getTeacherFrontInfo/{teacherId}")
    public CommonResult getTeacherFrontInfo(@ApiParam(name = "teacherId",value = "讲师id") @PathVariable String teacherId){
        EduTeacher teacher = eduTeacherService.getById(teacherId);
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id",teacherId);
        List<EduCourse> courseList = eduCourseService.list(wrapper);
        return CommonResult.ok().data("teacher",teacher).data("courseList",courseList);
    }
}
