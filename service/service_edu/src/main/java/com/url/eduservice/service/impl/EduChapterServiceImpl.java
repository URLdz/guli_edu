package com.url.eduservice.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.url.eduservice.entity.EduChapter;
import com.url.eduservice.entity.EduVideo;
import com.url.eduservice.entity.chapter.ChapterVo;
import com.url.eduservice.entity.chapter.VedioVo;
import com.url.eduservice.mapper.EduChapterMapper;
import com.url.eduservice.service.EduChapterService;
import com.url.eduservice.service.EduVideoService;
import com.url.servicebase.exceptionhandle.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author xidazhen
 * @since 2022-09-23
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> getChapterVedioByCourseId(String courseId) {
        //根据课程查询章节
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id",courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList(wrapperChapter);

        //根据章节查询小节
        QueryWrapper<EduVideo> wrapperVedio = new QueryWrapper<>();
        wrapperVedio.eq("course_id",courseId);
        List<EduVideo> eduVideoList = eduVideoService.list(wrapperVedio);

        //封装
        List<ChapterVo> finalList = new ArrayList<>();
        for (int i = 0; i < eduChapterList.size(); i++) {
            EduChapter eduChapter = eduChapterList.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            finalList.add(chapterVo);

            List<VedioVo> vediolList = new ArrayList<>();
            for (int j = 0; j < eduVideoList.size(); j++) {
                EduVideo eduVideo = eduVideoList.get(j);
                if(eduVideo.getChapterId().equals(eduChapter.getId())){
                    VedioVo vedioVo = new VedioVo();
                    BeanUtils.copyProperties(eduVideo,vedioVo);
                    vediolList.add(vedioVo);
                }
            }
            chapterVo.setChildren(vediolList);
        }
        return finalList;
    }

    @Override
    public Boolean deleteChapter(String chapterId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        int count = eduVideoService.count(wrapper);
        if (count != 0){
            throw new GuliException(20001,"不能删除存在课时的章节");
        }else{
            int i = baseMapper.deleteById(chapterId);
            return i>0;
        }
    }

    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }

}
