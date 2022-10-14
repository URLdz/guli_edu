package com.url.eduservice.controller;


import com.url.commonutils.CommonResult;
import com.url.eduservice.entity.EduChapter;
import com.url.eduservice.entity.chapter.ChapterVo;
import com.url.eduservice.entity.vo.CoursePublishVo;
import com.url.eduservice.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author xidazhen
 * @since 2022-09-23
 */
@Api(description = "课程章节管理")

@RestController
@RequestMapping("/eduservice/chapter")
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;

    @ApiOperation("课程大纲列表")
    @GetMapping("getChapterVedio/{courseId}")
    public CommonResult getChapterVedio(@ApiParam(name = "courseId", value = "课程ID", required = true) @PathVariable String courseId) {
        List<ChapterVo> list = eduChapterService.getChapterVedioByCourseId(courseId);
        return CommonResult.ok().data("allChapterVedio", list);
    }

    @ApiOperation("添加章节")
    @PostMapping("addChapter")
    public CommonResult addChapter(@RequestBody EduChapter eduChapter) {
        eduChapterService.save(eduChapter);
        return CommonResult.ok();
    }

    @ApiOperation("根据章节id查询")
    @GetMapping("getChapterInfo/{chapterId}")
    public CommonResult getChapterInfo(@ApiParam(name = "chapterId", value = "章节ID", required = true) @PathVariable String chapterId) {
        EduChapter eduChapter = eduChapterService.getById(chapterId);
        return CommonResult.ok().data("chapter", eduChapter);
    }

    @ApiOperation("修改章节")
    @PostMapping("updateChapter")
    public CommonResult updateChapter(@RequestBody EduChapter eduChapter) {
        eduChapterService.updateById(eduChapter);
        return CommonResult.ok();
    }

    @ApiOperation("删除章节")
    @DeleteMapping("deleteChapter/{chapterId}")
    public CommonResult deleteChapter(@ApiParam(name = "chapterId", value = "章节ID", required = true) @PathVariable String chapterId) {
        boolean i = eduChapterService.deleteChapter(chapterId);
        if (i) {
            return CommonResult.ok();
        } else {
            return CommonResult.error();
        }
    }

}

