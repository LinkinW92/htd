package com.skeqi.mes.util.wf;

/**
 * @author Lenovo
 * 全局通用工具类
 */
public class UniversalUtil {
    /**
     * 按时间戳和4位随机数生成编号
     * @return
     */
    public static String generateNumber(){
        //获取当前时间戳
        Long timeMillis = System.currentTimeMillis();
        //生成四位随机数
        Integer integer = (int)((Math.random()*9+1)*1000);
        return timeMillis.toString()+integer.toString();
    }
}
