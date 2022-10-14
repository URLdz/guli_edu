package com.url.eduservice.service;

import com.url.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.url.eduservice.entity.vo.TeacherQueryVo;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author xidazhen
 * @since 2022-09-17
 */
public interface EduTeacherService extends IService<EduTeacher> {

    Map getTeacherListPage(long current, long limit);

    Map pageTeacherCondition(long current, long limit, TeacherQueryVo teacherQueryVo);

    @Cacheable(key = "'selectTeacherFront'",value = "teacher")
    List<EduTeacher> getTeacherFront();

    Map<String,Object> getTeacherFrontList(long current, long limit);
}
