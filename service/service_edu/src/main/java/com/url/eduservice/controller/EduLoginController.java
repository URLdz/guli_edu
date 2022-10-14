package com.url.eduservice.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.url.commonutils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author xidazhen
 * @date 2022/9/20 - 10:35
 */
@Api(description = "后台登录管理")
@RestController
@RequestMapping("/eduservice/user")
  //解决跨域问题
public class EduLoginController {
    @ApiOperation(value = "登录")
    @PostMapping("login")
    public CommonResult login() {

        return CommonResult.ok().data("token","admin");
    }

    @ApiOperation(value = "用户信息")
    @GetMapping("info")
    public CommonResult info(){
        return CommonResult.ok()
                .data("roles","[admin]")
                .data("name","admin")
                .data("avatar","https://guli-edu-dz.oss-cn-shenzhen.aliyuncs.com/2022/09/21/cb2cadc145b34baba6a33eeb55981adcfile.png");
    }
}
