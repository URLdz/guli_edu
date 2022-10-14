package com.url.aclservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.url.aclservice.entity.Permission;
import com.url.aclservice.service.IndexService;
import com.url.aclservice.service.PermissionService;
import com.url.commonutils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author xidazhen
 * @date 2022/10/12 - 16:23
 */
@Api
@RestController
@RequestMapping("/admin/acl/index")
public class IndexController {

    @Autowired
    private IndexService indexService;

    /**
     * 根据token获取用户信息
     */
    @GetMapping("info")
    public CommonResult info(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> userInfo = indexService.getUserInfo(username);
        return CommonResult.ok().data(userInfo);
    }

    /**
     * 获取菜单
     * @return
     */
    @GetMapping("menu")
    public CommonResult getMenu(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<JSONObject> permissionList = indexService.getMenu(username);
        return CommonResult.ok().data("permissionList", permissionList);
    }

    @PostMapping("logout")
    public CommonResult logout(){
        return CommonResult.ok();
    }

}
