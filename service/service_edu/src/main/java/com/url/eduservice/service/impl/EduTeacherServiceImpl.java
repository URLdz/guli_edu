package com.url.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.url.eduservice.entity.EduTeacher;
import com.url.eduservice.entity.vo.TeacherQueryVo;
import com.url.eduservice.mapper.EduTeacherMapper;
import com.url.eduservice.service.EduCourseService;
import com.url.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author xidazhen
 * @since 2022-09-17
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Autowired
    public EduCourseService eduCourseService;

    @Override
    public Map getTeacherListPage(long current, long limit) {
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        baseMapper.selectPage(pageTeacher,null);
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        Map map = new HashMap();
        map.put("total", total);
        map.put("rows", records);
        return map;
    }

    @Override
    public Map pageTeacherCondition(long current, long limit, TeacherQueryVo teacherQueryVo) {
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        //拼接条件
        String name = teacherQueryVo.getName();
        Integer level = teacherQueryVo.getLevel();
        String begin = teacherQueryVo.getBegin();
        String end = teacherQueryVo.getEnd();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_modified", end);
        }

        wrapper.orderByDesc("gmt_create");
        baseMapper.selectPage(pageTeacher, wrapper);
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        Map map = new HashMap();
        map.put("total", total);
        map.put("rows", records);
        return map;
    }

    /**
     * 前台名师
     * @return
     */
    @Override
    public List<EduTeacher> getTeacherFront() {
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 4");
        List<EduTeacher> eduTeachers = baseMapper.selectList(wrapper);
        return eduTeachers;
    }

    /**
     * 前台分页查询讲师
     * @param current
     * @param limit
     * @return
     */
    @Override
    public Map<String,Object> getTeacherFrontList(long current, long limit) {
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        baseMapper.selectPage(pageTeacher,wrapper);

        List<EduTeacher> records = pageTeacher.getRecords();
        long size = pageTeacher.getSize();
        long total = pageTeacher.getTotal();
        long pages = pageTeacher.getPages();
        boolean hasNext = pageTeacher.hasNext();
        boolean hasPrevious = pageTeacher.hasPrevious();

        Map<String,Object> map = new HashMap<>();
        map.put("current",current);
        map.put("records",records);
        map.put("size",size);
        map.put("total",total);
        map.put("pages",pages);
        map.put("hasNext",hasNext);
        map.put("hasPrevious",hasPrevious);

        return map;
    }

}
