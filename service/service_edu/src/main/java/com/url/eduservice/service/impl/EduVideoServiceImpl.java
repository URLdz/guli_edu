package com.url.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.url.commonutils.CommonResult;
import com.url.eduservice.client.VodClient;
import com.url.eduservice.entity.EduVideo;
import com.url.eduservice.mapper.EduVideoMapper;
import com.url.eduservice.service.EduVideoService;
import com.url.servicebase.exceptionhandle.GuliException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author xidazhen
 * @since 2022-09-23
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;

    @Override
    public boolean deleteVideo(String videoId) {
        int i = baseMapper.deleteById(videoId);

        return i>0;
    }

    @Override
    public Boolean removeVideoByCourseId(String courseId){
        QueryWrapper<EduVideo> wrappervideo = new QueryWrapper<>();
        wrappervideo.eq("course_id",courseId);
        //此处只需要查出课时列表中的视频id 使用 QueryWrapper提供的查询单字段方法即可
        wrappervideo.select("video_source_id");
        List<EduVideo> eduVideoList = baseMapper.selectList(wrappervideo);
        List<String> videoIds = new ArrayList<>();
        for (int i = 0; i < eduVideoList.size(); i++) {
            EduVideo eduVideo = eduVideoList.get(i);
            String videoSourceId = eduVideo.getVideoSourceId();
            if (!StringUtils.isEmpty(videoSourceId)){
                videoIds.add(videoSourceId);
            }
        }
        if (videoIds.size() > 0){
            CommonResult commonResult = vodClient.deleteBatch(videoIds);
            if (commonResult.getCode() == 20001){
                throw new GuliException(20001,"删除视频失败，熔断器生效");
            }
        }else{
            System.out.println("不存在需要删除的视频");
        }
        QueryWrapper<EduVideo> wrapperCourse = new QueryWrapper<>();
        wrapperCourse.eq("course_id",courseId);
        baseMapper.delete(wrapperCourse);
        return true;
    }
}
