package com.url.eduservice.service;

import com.url.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.url.eduservice.entity.chapter.ChapterVo;
import com.url.eduservice.entity.vo.CoursePublishVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author xidazhen
 * @since 2022-09-23
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVedioByCourseId(String courseId);

    Boolean deleteChapter(String chapterId);

    void removeChapterByCourseId(String courseId);
}
