package com.url.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.url.eduservice.client.UcenterClient;
import com.url.eduservice.entity.EduComment;
import com.url.eduservice.mapper.EduCommentMapper;
import com.url.eduservice.service.EduCommentService;
import com.url.servicebase.exceptionhandle.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author xidazhen
 * @since 2022-10-09
 */
@Service
public class EduCommentServiceImpl extends ServiceImpl<EduCommentMapper, EduComment> implements EduCommentService {

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public Map getCommentListPage(Page<EduComment> pageComment,String courseId) {
        Map map = new HashMap<>();
        QueryWrapper<EduComment> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");
        wrapper.eq("course_id",courseId);
        baseMapper.selectPage(pageComment,wrapper);
        long current = pageComment.getCurrent();
        List<EduComment> records = pageComment.getRecords();
        long size = pageComment.getSize();
        long pages = pageComment.getPages();
        long total = pageComment.getTotal();
        boolean hasNext = pageComment.hasNext();
        boolean hasPrevious = pageComment.hasPrevious();
        map.put("pages",pages);
        map.put("current",current);
        map.put("records",records);
        map.put("size",size);
        map.put("total",total);
        map.put("hasNext",hasNext);
        map.put("hasPrevious",hasPrevious);
        return map;
    }

    @Override
    public void addComment(EduComment eduComment, String memberId) {

        eduComment.setMemberId(memberId);
        Map<String,String> map = new HashMap<>();
        map = ucenterClient.getMemberInfoComment(memberId);
        if (map == null){
            throw new GuliException(20001,"当前用户信息不存在");
        }
        eduComment.setNickname(map.get("nickName"));
        eduComment.setAvatar(map.get("avatar"));
        int insert = baseMapper.insert(eduComment);
        if (insert == 0){
            throw new GuliException(20001,"评论失败");
        }
    }
}
