package com.url.eduservice.controller;


import com.url.commonutils.CommonResult;
import com.url.eduservice.entity.EduTeacher;
import com.url.eduservice.entity.vo.TeacherQueryVo;
import com.url.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author xidazhen
 * @since 2022-09-17
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")

public class EduTeacherController {

    //注入 service
    @Autowired
    private EduTeacherService eduTeacherService;

    //1.查询讲师的所有数据
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public CommonResult findAllTeacher() {
        List<EduTeacher> list = eduTeacherService.list(null);
        return CommonResult.ok().data("item", list);
    }

    //2.逻辑删除讲师的方法
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public CommonResult removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id) {
        boolean flag = eduTeacherService.removeById(id);
        if (flag) {
            return CommonResult.ok();
        }
        return CommonResult.error();
    }

    //3.分页查询讲师
    @ApiOperation(value = "分页查询讲师")
    @GetMapping("pageTeacher/{current}/{limit}")
    public CommonResult pageListTeacher(
            @ApiParam(name = "current", value = "当前页", required = true) @PathVariable long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable long limit) {
        Map map = eduTeacherService.getTeacherListPage(current, limit);
        return CommonResult.ok().data(map);
    }

    //4.条件分页查询

    /**
     * @RequestBody ->Post请求
     * 将json数据封装到对应的对象里边
     */
    @ApiOperation(value = "条件分页查询讲师")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public CommonResult pageTeacherCondition(
            @ApiParam(name = "current", value = "当前页", required = true) @PathVariable long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable long limit,
            @RequestBody(required = false) TeacherQueryVo teacherQueryVo) {
        Map map = eduTeacherService.pageTeacherCondition(current, limit, teacherQueryVo);
        return CommonResult.ok().data(map);

    }

    //5.添加讲师方法
    @ApiOperation(value = "添加讲师")
    @PostMapping("addTeacher")
    public CommonResult addTeacher(@ApiParam(name = "eduTeacher", value = "eduTeacher对象", required = true) @RequestBody EduTeacher eduTeacher) {
        boolean save = eduTeacherService.save(eduTeacher);
        if (save) {
            return CommonResult.ok();
        } else {
            return CommonResult.error();
        }
    }

    @ApiOperation(value = "根据id查询讲师")
    @GetMapping("getTeacher/{id}")
    public CommonResult getTeacherById(
            @ApiParam(name = "id", value = "讲师id", required = true) @PathVariable("id") String id) {
        EduTeacher teacher = eduTeacherService.getById(id);
        if (teacher != null) {
            return CommonResult.ok().data("teacher", teacher);
        } else {
            return CommonResult.error();
        }
    }

    //修改本应该用 PutMapping 但通过 RequetBody 传递参数需要用到 PostMapping
    @ApiOperation(value = "修改讲师信息")
    @PostMapping("updateTeacher")
    public CommonResult updateTeacher(@ApiParam(name = "eduTeacher", value = "eduTeacher对象", required = true) @RequestBody EduTeacher eduTeacher) {
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if (flag) {
            return CommonResult.ok();
        } else {
            return CommonResult.error();
        }
    }
}

