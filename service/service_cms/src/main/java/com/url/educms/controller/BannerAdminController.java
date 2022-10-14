package com.url.educms.controller;


import com.url.commonutils.CommonResult;
import com.url.educms.entity.CrmBanner;
import com.url.educms.service.CrmBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 首页banner表 前端控制器 管理员
 * </p>
 *
 * @author xidazhen
 * @since 2022-10-03
 */
@Api(description = "轮播图-管理员板块")
@RestController
@RequestMapping("/educms/banneradmin")

public class BannerAdminController {
    @Autowired
    private CrmBannerService crmBannerService;

    @ApiOperation("分页查询轮播图")
    @GetMapping("pageBanner/{current}/{limit}")
    public CommonResult pageBanner(@ApiParam(name = "current", value = "当前页", required = true) @PathVariable long current,
                                   @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable long limit) {
        Map map = crmBannerService.pageBanner(current, limit);
        return CommonResult.ok().data(map);
    }

    @ApiOperation("添加轮播图")
    @PostMapping("addBanner")
    public CommonResult addBanner(@RequestBody CrmBanner crmBanner) {
        crmBannerService.save(crmBanner);
        return CommonResult.ok();
    }

    @ApiOperation("修改轮播图")
    @PostMapping("updateBanner")
    public CommonResult updateBannerById(@RequestBody CrmBanner crmBanner) {
        crmBannerService.updateById(crmBanner);
        return CommonResult.ok();
    }

    @ApiOperation("删除轮播图")
    @DeleteMapping("delete/{id}")
    public CommonResult deleteById(@ApiParam(name = "id", value = "轮播图id") @PathVariable String id) {
        crmBannerService.removeById(id);
        return CommonResult.ok();
    }

    @ApiOperation("根据id获取轮播图信息")
    @GetMapping("get/{id}")
    public CommonResult get(@ApiParam(name = "id", value = "轮播图id") @PathVariable String id){
        CrmBanner crmBanner = crmBannerService.getById(id);
        return CommonResult.ok().data("crmBanner",crmBanner);
    }
}

