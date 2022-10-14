package com.url.eduorder.controller;


import com.url.commonutils.CommonResult;
import com.url.eduorder.service.PayLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author xidazhen
 * @since 2022-10-10
 */
@Api(description = "支付管理")

@RestController
@RequestMapping("/eduorder/paylog")
public class PayLogController {
    @Autowired
    private PayLogService payLogService;

    @ApiOperation("生成支付页面微信二维码")
    @GetMapping("createNative/{orderNo}")
    public CommonResult createNative(@ApiParam(name = "orderNo", value = "订单编号") @PathVariable String orderNo) {
        Map map = payLogService.createNative(orderNo);
        System.out.println("****二维码map" + map);
        return CommonResult.ok().data(map);
    }

    @ApiOperation("查询订单状态")
    @GetMapping("queryPayStatus/{orderNo}")
    public CommonResult queryPayStatus(@ApiParam(name = "orderNo", value = "订单编号") @PathVariable String orderNo) {
        Map<String, String> map = payLogService.queryPayStatus(orderNo);
        System.out.println("****查询订单状态的map" + map);
        if (map == null) {
            return CommonResult.error().message("支付出错了");
        }
        if (map.get("trade_state").equals("SUCCESS")) {
            payLogService.updateOrdersStatus(map);
            return CommonResult.ok().message("支付成功");
        }
        return CommonResult.ok().code(25000).message("支付中");
    }
}

