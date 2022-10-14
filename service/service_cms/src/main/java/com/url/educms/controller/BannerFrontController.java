package com.url.educms.controller;


import com.url.commonutils.CommonResult;
import com.url.educms.entity.CrmBanner;
import com.url.educms.service.CrmBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 首页banner表 前端控制器 用户
 * </p>
 *
 * @author xidazhen
 * @since 2022-10-03
 */
@Api(description = "轮播图-用户板块")
@RestController
@RequestMapping("/educms/bannerfront")

public class BannerFrontController {
    @Autowired
    private CrmBannerService crmBannerService;

    @Cacheable(key = "'selectBannerList'",value = "banner")
    @ApiOperation("查询轮播图")
    @GetMapping("getAllBanner")
    public CommonResult getAllBanner() {
        List<CrmBanner> list = crmBannerService.getAllBanner();
        return CommonResult.ok().data("list",list);
    }
}

