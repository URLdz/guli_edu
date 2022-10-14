package com.url.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.url.eduservice.entity.EduCourse;
import com.url.eduservice.entity.EduCourseDescription;
import com.url.eduservice.entity.frontVo.CourseFrontVo;
import com.url.eduservice.entity.frontVo.CourseWebVo;
import com.url.eduservice.entity.vo.CourseInfoVo;
import com.url.eduservice.entity.vo.CoursePublishVo;
import com.url.eduservice.entity.vo.CourseQueryVo;
import com.url.eduservice.mapper.EduCourseMapper;
import com.url.eduservice.service.EduChapterService;
import com.url.eduservice.service.EduCourseDescriptionService;
import com.url.eduservice.service.EduCourseService;
import com.url.eduservice.service.EduVideoService;
import com.url.servicebase.exceptionhandle.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author xidazhen
 * @since 2022-09-23
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;
    @Autowired
    private EduChapterService eduChapterService;
    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert == 0){
            throw new GuliException(20001,"添加课程信息失败");
        }

        String cid = eduCourse.getId();

        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescription.setId(cid);
        eduCourseDescriptionService.save(eduCourseDescription);
        return cid;
    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);


        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(eduCourseDescription.getDescription());
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if (update ==0){
            throw new GuliException(20001,"修改课程信息失败");
        }

        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(courseInfoVo.getId());
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescriptionService.updateById(eduCourseDescription);
    }

    @Override
    public CoursePublishVo getPublishCourseInfo(String id) {
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(id);
        return publishCourseInfo;
    }

    @Override
    public Map getCourseListPage(long current, long limit) {
        Page<EduCourse> pageCourse = new Page<>(current,limit);
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("status","Normal");
        baseMapper.selectPage(pageCourse, wrapper);
        long total = pageCourse.getTotal();
        List<EduCourse> records = pageCourse.getRecords();
        Map map = new HashMap();
        map.put("total", total);
        map.put("rows", records);
        return map;
    }

    @Override
    public Map pageCourseCondition(long current, long limit, CourseQueryVo courseQueryVo) {
        Page<EduCourse> pageCourse = new Page<>(current, limit);
        //构建条件
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();

        //拼接条件
        String title = courseQueryVo.getTitle();
        String status = courseQueryVo.getStatus();
        if (!StringUtils.isEmpty(title)) {
            wrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(status)) {
            wrapper.eq("status", status);
        }
        wrapper.orderByDesc("gmt_create");
        baseMapper.selectPage(pageCourse, wrapper);
        long total = pageCourse.getTotal();
        List<EduCourse> records = pageCourse.getRecords();
        Map map = new HashMap();
        map.put("total", total);
        map.put("rows", records);
        return map;
    }

    @Override
    public Boolean deleteCourse(String courseId) {
        Boolean flag = eduVideoService.removeVideoByCourseId(courseId);
        if (flag != true){
            return false;
        }
        eduChapterService.removeChapterByCourseId(courseId);
        eduCourseDescriptionService.removeById(courseId);
        int i = baseMapper.deleteById(courseId);
        if (i == 0){
            throw new GuliException(20001,"删除失败");
        }else {
            return true;
        }
    }

    @Override
    public List<EduCourse> getCourseFront() {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 4");
        List<EduCourse> eduCourses = baseMapper.selectList(wrapper);
        return eduCourses;
    }

    @Override
    public Map<String, Object> getCourseFrontList(Page<EduCourse> pageParam, CourseFrontVo courseFrontVo) {
        //2 根据讲师id查询所讲课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        //判断条件值是否为空，不为空拼接
        if(!StringUtils.isEmpty(courseFrontVo.getSubjectParentId())) { //一级分类
            wrapper.eq("subject_parent_id",courseFrontVo.getSubjectParentId());
        }
        if(!StringUtils.isEmpty(courseFrontVo.getSubjectId())) { //二级分类
            wrapper.eq("subject_id",courseFrontVo.getSubjectId());
        }
        if(!StringUtils.isEmpty(courseFrontVo.getBuyCountSort())) { //关注度
            wrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(courseFrontVo.getGmtCreateSort())) { //最新
            wrapper.orderByDesc("gmt_create");
        }

        if (!StringUtils.isEmpty(courseFrontVo.getPriceSort())) {//价格
            wrapper.orderByDesc("price");
        }

        baseMapper.selectPage(pageParam,wrapper);

        List<EduCourse> records = pageParam.getRecords();
        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long size = pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();//下一页
        boolean hasPrevious = pageParam.hasPrevious();//上一页

        //把分页数据获取出来，放到map集合
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        //map返回
        return map;
    }

    @Override
    public CourseWebVo getBaseCourseInfo(String courseId) {
        return baseMapper.getBaseCourseInfo(courseId);
    }
}
