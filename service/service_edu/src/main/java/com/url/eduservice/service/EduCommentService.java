package com.url.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.url.eduservice.entity.EduComment;

import java.util.Map;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author xidazhen
 * @since 2022-10-09
 */
public interface EduCommentService extends IService<EduComment> {

    Map<String, Object> getCommentListPage(Page<EduComment> pageComment,String courseId);

    void addComment(EduComment eduComment, String memberId);
}
