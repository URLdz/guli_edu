package com.url.eduservice.controller.front;

import com.url.commonutils.CommonResult;
import com.url.eduservice.entity.EduCourse;
import com.url.eduservice.entity.EduTeacher;
import com.url.eduservice.service.EduCourseService;
import com.url.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xidazhen
 * @date 2022/10/3 - 21:09
 */
@Api(description = "前台首页")
@RestController
@RequestMapping("/eduservice/indexfront")

public class IndexFrontController {

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduTeacherService eduTeacherService;



    @ApiOperation("首页课程&名师")
    @GetMapping("index")
    public CommonResult index(){
        //前四课程
        List<EduCourse> eduCourses = eduCourseService.getCourseFront();
        //前四名师
        List<EduTeacher> eduTeachers = eduTeacherService.getTeacherFront();
        return CommonResult.ok().data("courseList",eduCourses).data("teacherList",eduTeachers);
    }
}
