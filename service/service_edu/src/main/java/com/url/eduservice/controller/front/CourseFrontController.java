package com.url.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.url.commonutils.CommonResult;
import com.url.commonutils.JwtUtils;
import com.url.commonutils.ordervo.CourseWebVoOrder;
import com.url.eduservice.client.OrderClient;
import com.url.eduservice.entity.EduCourse;
import com.url.eduservice.entity.EduTeacher;
import com.url.eduservice.entity.chapter.ChapterVo;
import com.url.eduservice.entity.frontVo.CourseFrontVo;
import com.url.eduservice.entity.frontVo.CourseWebVo;
import com.url.eduservice.service.EduChapterService;
import com.url.eduservice.service.EduCourseService;
import com.url.eduservice.service.EduTeacherService;
import com.url.servicebase.exceptionhandle.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author xidazhen
 * @date 2022/10/7 - 22:04
 */
@Api(description = "前台课程板块")
@RestController

@RequestMapping("/eduservice/coursefront")
public class CourseFrontController {

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private OrderClient orderClient;

    @ApiOperation("分页查询课程")
    @PostMapping("getCourseFront/{current}/{limit}")
    public CommonResult getTeacherFront(@ApiParam(name = "current", value = "当前页", required = true) @PathVariable long current,
                                        @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable long limit,
                                        @RequestBody(required = false) CourseFrontVo courseFrontVo) {
        Page<EduCourse> pageCourse = new Page<>(current,limit);
        Map<String,Object> map = eduCourseService.getCourseFrontList(pageCourse,courseFrontVo);
        return CommonResult.ok().data(map);
    }

    @ApiOperation("前台查询课程详细信息")
    @GetMapping("getFrontCourseInfo/{courseId}")
    public CommonResult getFrontCourseInfo(@ApiParam(name = "courseId", value = "课程id", required = true)@PathVariable String courseId, HttpServletRequest request){
        CourseWebVo courseWebVo = eduCourseService.getBaseCourseInfo(courseId);
        List<ChapterVo> chapterVedioList = eduChapterService.getChapterVedioByCourseId(courseId);
        String token = JwtUtils.getMemberIdByJwtToken(request);
        boolean buyCourse =false;
        String message = null;
        if (!StringUtils.isEmpty(token)){
            buyCourse = orderClient.isBuyCourse(courseId, token);
        }
        return CommonResult.ok().data("courseWebVo",courseWebVo).data("chapterVedioList",chapterVedioList).data("isBuy",buyCourse);
    }

    @ApiOperation("订单远程--根据id查询课程详细信息")
    @PostMapping("getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable("id") String id){
        CourseWebVo baseCourseInfo = eduCourseService.getBaseCourseInfo(id);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(baseCourseInfo,courseWebVoOrder);
        return courseWebVoOrder;
    }

}
