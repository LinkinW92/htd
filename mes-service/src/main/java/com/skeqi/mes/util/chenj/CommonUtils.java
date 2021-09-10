package com.skeqi.mes.util.chenj;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Validator;
import com.skeqi.common.constant.Constants;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.utils.redis.RedisCache;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author ChenJ
 * @date 2021/7/14
 * @Classname CommonUtils
 * @Description 公共工具类
 */
@Slf4j
public class CommonUtils {
    /**
     * 响应拦截信息
     *
     * @param response
     * @param value
     * @throws IOException
     */
    public static void resultValue(ServletResponse response, String value, Integer code) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        StringBuilder sb = new StringBuilder();
        sb.append("{").append('"').append("code").append('"').append(":").append(code).append(",")
                .append('"').append("msg").append('"').append(":").append('"').append(value).
                append('"').append(",").append('"').append("result").append('"').append(":").append("null").append("}");
        PrintWriter out = response.getWriter();
        out.println(sb.toString());
        return;
    }

	/**
	 *  获取Redis缓存配置
	 * @param redisCache redis对象
	 * @param key key
	 * @param annotation 注释
	 * @return
	 */
    public static String getRedisValue(RedisCache redisCache,String key,String annotation){

		String configValue = Convert.toStr(redisCache.getCacheObject(Constants.SYS_CONFIG_KEY + key));
		if (Validator.isNotEmpty(configValue)) {
			return configValue;
		}else{
			log.error("获取"+annotation+"失败，请刷新缓存后再试试");
			throw new CustomException("网络异常");
		}
	}
}
