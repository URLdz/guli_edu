package com.url.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.url.commonutils.CommonResult;
import com.url.commonutils.JwtUtils;
import com.url.eduservice.entity.EduComment;
import com.url.eduservice.service.EduCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author xidazhen
 * @since 2022-10-09
 */
@Api(description = "评论管理")

@RestController
@RequestMapping("/eduservice/comment")
public class EduCommentController {
    @Autowired
    private EduCommentService eduCommentService;

    @ApiOperation("获取评论列表")
    @GetMapping("getCommentListPage/{current}/{limit}")
    public CommonResult getCommentListPage( @ApiParam(name = "current", value = "当前页码", required = true) @PathVariable Long current,
                                            @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable Long limit,
                                            @ApiParam(name = "courseId", value = "课程id", required = false) String courseId) {
        Page<EduComment> pageComment = new Page<>(current, limit);
        Map map = eduCommentService.getCommentListPage(pageComment,courseId);
        return CommonResult.ok().data(map);
    }

    @ApiOperation("新增评论")
    @PostMapping("addComment")
    public CommonResult addComment(@RequestBody EduComment eduComment,HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if(StringUtils.isEmpty(memberId)){
            return CommonResult.error().code(28004).message("未登录无法评论，即将前往登陆页面");
        }
        eduCommentService.addComment(eduComment, memberId);
        return CommonResult.ok();
    }
}

