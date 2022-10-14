package com.url.edustatistics.controller;


import com.url.commonutils.CommonResult;
import com.url.edustatistics.service.StatisticsDailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hamcrest.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author xidazhen
 * @since 2022-10-10
 */
@Api(description = "统计管理")

@RestController
@RequestMapping("/edustatistics/statistics")
public class StatisticsDailyController {
    @Autowired
    private StatisticsDailyService statisticsDailyService;

    @ApiOperation("注册统计")
    @GetMapping("registerCount/{day}")
    public CommonResult registerCount(@PathVariable String day){
        statisticsDailyService.registerCount(day);
        return CommonResult.ok();
    }

    @ApiOperation("图表显示")
    @GetMapping("showData/{type}/{begin}/{end}")
    public CommonResult showData(@PathVariable String type,@PathVariable String begin,@PathVariable String  end){
        Map<String,Object> map = statisticsDailyService.showData(type,begin,end);
        return CommonResult.ok().data(map);
    }
}

