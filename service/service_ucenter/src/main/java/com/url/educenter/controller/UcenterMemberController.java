package com.url.educenter.controller;


import com.url.commonutils.CommonResult;
import com.url.commonutils.JwtUtils;
import com.url.commonutils.ordervo.UcenterMemberOrder;
import com.url.educenter.entity.UcenterMember;
import com.url.educenter.entity.vo.MemberVo;
import com.url.educenter.entity.vo.RegisterVo;
import com.url.educenter.service.UcenterMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author xidazhen
 * @since 2022-10-05
 */
@Api(description = "前台用户登录")
@RestController

@RequestMapping("/educenter/member")
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService ucenterMemberService;

    @ApiOperation("用户登录")
    @PostMapping("login")
    public CommonResult loginUser(@RequestBody MemberVo memberVo) {
        String token = ucenterMemberService.login(memberVo);
        return CommonResult.ok().data("token", token);
    }

    @ApiOperation("用户注册")
    @PostMapping("register")
    public CommonResult registerUser(@RequestBody RegisterVo registerVo) {
        ucenterMemberService.register(registerVo);
        return CommonResult.ok();
    }

    @ApiOperation("根据token获取用户信息")
    @GetMapping("getMemberInfo")
    public CommonResult getMemberInfo(HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember member = ucenterMemberService.getById(memberId);
        return CommonResult.ok().data("userInfo",member);
    }

    /**
     * 需要获取的值只有两个 nickname avatar 用 map返回即可
     * @param memberId
     * @return
     */
    @ApiOperation("评论远程--根据id获取用户信息")
    @PostMapping("getMemberInfoComment/{memberId}")
    public Map getMemberInfoComment(@PathVariable("memberId") String memberId){
        UcenterMember member = ucenterMemberService.getById(memberId);
        String nickname = member.getNickname();
        String avatar = member.getAvatar();
        Map map = new HashMap<>();
        map.put("nickname",nickname);
        map.put("avatar",avatar);
        System.out.println(map);
        return map;
    }

    @ApiOperation("订单远程--根据id获取用户信息")
    @PostMapping("getMemberInfoOrder/{memberId}")
    public UcenterMemberOrder getMemberInfoOrder(@PathVariable("memberId") String memberId){
        UcenterMember member = ucenterMemberService.getById(memberId);
        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(member,ucenterMemberOrder);
        return ucenterMemberOrder;
    }

    @GetMapping("countRegister/{day}")
    public CommonResult countRegister(@PathVariable String day){
        Integer count = ucenterMemberService.countRegister(day);
        return CommonResult.ok().data("countRegister",count);
    }

}

