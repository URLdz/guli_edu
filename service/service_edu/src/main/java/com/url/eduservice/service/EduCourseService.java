package com.url.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.url.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.url.eduservice.entity.frontVo.CourseFrontVo;
import com.url.eduservice.entity.frontVo.CourseWebVo;
import com.url.eduservice.entity.vo.CourseInfoVo;
import com.url.eduservice.entity.vo.CoursePublishVo;
import com.url.eduservice.entity.vo.CourseQueryVo;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author xidazhen
 * @since 2022-09-23
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo getPublishCourseInfo(String id);

    Map getCourseListPage(long current, long limit);

    Map pageCourseCondition(long current, long limit, CourseQueryVo courseQueryVo);

    Boolean deleteCourse(String courseId);

    @Cacheable(key = "'selectCourseFront'",value = "course")
    List<EduCourse> getCourseFront();

    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(String courseId);
}
