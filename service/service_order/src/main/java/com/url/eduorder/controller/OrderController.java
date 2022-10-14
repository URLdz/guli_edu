package com.url.eduorder.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.url.commonutils.CommonResult;
import com.url.commonutils.JwtUtils;
import com.url.eduorder.entity.Order;
import com.url.eduorder.service.OrderService;
import com.url.servicebase.exceptionhandle.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author xidazhen
 * @since 2022-10-10
 */

@Api(description = "订单管理")
@RestController
@RequestMapping("/eduorder/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation("生成订单")
    @PostMapping("createOrder/{courseId}")
    public CommonResult createOrder(@PathVariable("courseId") String courseId, HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if(StringUtils.isEmpty(memberId)){
            return CommonResult.error().code(28004).message("请登录");
        }
        String orderNo = orderService.createOrder(courseId,memberId);
        if (orderNo == null){
            throw new GuliException(20001,"订单生成失败");
        }
        return CommonResult.ok().data("orderNo",orderNo);
    }

    @ApiOperation("根据订单编号获取订单信息")
    @GetMapping("getOrderInfo/{orderId}")
    public CommonResult getOrderInfo(@ApiParam(name = "orderId",value = "订单id") @PathVariable String orderId){
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderId);
        Order order = orderService.getOne(wrapper);
        return CommonResult.ok().data("order",order);
    }

    @ApiOperation("根据课程id和用户id查询订单状态")
    @GetMapping("isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@ApiParam(name = "courseId",value = "订单id") @PathVariable String courseId,
                                    @ApiParam(name = "memberId",value = "用户id") @PathVariable String memberId){
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.eq("member_id",memberId);
        wrapper.eq("status",1);
        int count = orderService.count(wrapper);
        if (count > 0){
            return true;
        }
        return false;
    }

}

