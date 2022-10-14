package com.url.servicebase.exceptionhandle;

import com.url.commonutils.CommonResult;
import com.url.commonutils.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xidazhen
 * @date 2022/9/18 - 11:11
 */
@ControllerAdvice
@Slf4j
public class GlobalExeceptionHandle {

    //统一全局异常处理
    @ExceptionHandler(Exception.class) //指定出现什么异常执行这个方法
    @ResponseBody //返回数据
    public CommonResult error(Exception e) {
        e.printStackTrace();
        return CommonResult.error().message("执行了全局异常处理");
    }

    //特定异常处理
    @ExceptionHandler(ArithmeticException.class) //指定出现什么异常执行这个方法
    @ResponseBody //返回数据
    public CommonResult ArithmeticException(ArithmeticException e) {
        e.printStackTrace();
        return CommonResult.error().message("执行了ArithmeticException异常处理");
    }

    //指定异常处理
    @ExceptionHandler(GuliException.class) //指定出现什么异常执行这个方法
    @ResponseBody //返回数据
    public CommonResult GuliException(GuliException e) {
        log.error(ExceptionUtil.getMessage(e));
        e.printStackTrace();
        return CommonResult.error().code(e.getCode()).message(e.getMsg());
    }
}
