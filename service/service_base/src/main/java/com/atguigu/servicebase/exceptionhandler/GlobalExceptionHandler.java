package com.atguigu.servicebase.exceptionhandler;

import com.atguigu.commonutils.R;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @version 1.0
 * @auther 刘欢龙
 */
@Component
public class GlobalExceptionHandler {

    //指定出现什么异常执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody//为了返回数据
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了全局异常处理。。");
    }

    //特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException a){
        a.printStackTrace();
        return R.error().message("被除数不能为0");
    }

    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public R error(GuliException guliException){
        guliException.printStackTrace();
        return R.error().code(guliException.getCode()).message(guliException.getMsg());
    }

}
