package com.url.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.url.eduservice.entity.EduSubject;
import com.url.eduservice.entity.excel.SubjectData;
import com.url.eduservice.entity.subject.OneSubject;
import com.url.eduservice.entity.subject.TwoSubject;
import com.url.eduservice.listen.SubjectExcelListener;
import com.url.eduservice.mapper.EduSubjectMapper;
import com.url.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author xidazhen
 * @since 2022-09-22
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file,EduSubjectService eduSubjectService) {
        try {
            InputStream in = file.getInputStream();
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //1.查询所有一级分类    parent_id=0
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id","0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);
        //2.查询所有二级分类    parent_id!=0
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperOne.ne("parent_id","0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);

        //封装数据
        List<OneSubject> finalSubjectList = new ArrayList<>();
        //eduSubject->oneSubject  -->加入finalSubjectList
        for (int i = 0; i < oneSubjectList.size(); i++) {
            //封装一级分类
            EduSubject eduSubject = oneSubjectList.get(i);
            OneSubject oneSubject = new OneSubject();
            //转换方式一
           /* oneSubject.setId(eduSubject.getId());
            oneSubject.setTitle(eduSubject.getTitle());
            */
            //转换方式二
            BeanUtils.copyProperties(eduSubject,oneSubject);

            //将数据添加到封装的 list集合
            finalSubjectList.add(oneSubject);

            //封装二级分类
            ArrayList<TwoSubject> twoFinalSubjectList = new ArrayList<>();
            for (int j = 0; j < twoSubjectList.size(); j++) {
                EduSubject tSubject = twoSubjectList.get(j);
                //判断二级分类所属
                if (tSubject.getParentId().equals(eduSubject.getId())){
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(tSubject,twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }
            }
            oneSubject.setChildren(twoFinalSubjectList);
        }
        return finalSubjectList;
    }
}
