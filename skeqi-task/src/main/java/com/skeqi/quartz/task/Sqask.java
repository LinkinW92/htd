package com.skeqi.quartz.task;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Component;

/**
 * 定时任务调度测试
 *
 * @author skeqi
 */
@Component("sqTask")
public class Sqask
{
    public void sqMultipleParams(String s, Boolean b, Long l, Double d, Integer i)
    {
        Console.log(StrUtil.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void sqParams(String params)
    {
        Console.log("执行有参方法：" + params);
    }

    public void sqNoParams()
    {
        Console.log("执行无参方法");
    }
}
