package com.url.eduservice.service;

import com.url.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author xidazhen
 * @since 2022-09-23
 */
public interface EduVideoService extends IService<EduVideo> {

    boolean deleteVideo(String videoId);

    Boolean removeVideoByCourseId(String courseId);


}
