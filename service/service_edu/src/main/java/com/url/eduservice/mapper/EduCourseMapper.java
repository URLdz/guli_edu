package com.url.eduservice.mapper;

import com.sun.tracing.dtrace.ModuleAttributes;
import com.url.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.url.eduservice.entity.frontVo.CourseWebVo;
import com.url.eduservice.entity.vo.CoursePublishVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author xidazhen
 * @since 2022-09-23
 */
@Mapper
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    CoursePublishVo getPublishCourseInfo(String courseId);

    CourseWebVo getBaseCourseInfo(String courseId);
}
