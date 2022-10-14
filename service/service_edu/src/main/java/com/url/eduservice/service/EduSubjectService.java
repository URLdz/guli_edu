package com.url.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.url.eduservice.entity.EduSubject;
import com.url.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author xidazhen
 * @since 2022-09-22
 */
public interface EduSubjectService extends IService<EduSubject> {
    void saveSubject(MultipartFile file,EduSubjectService eduSubjectService);

    List<OneSubject> getAllOneTwoSubject();
}
