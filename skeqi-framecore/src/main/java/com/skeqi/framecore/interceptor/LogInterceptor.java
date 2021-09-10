package com.skeqi.framecore.interceptor;

import cn.hutool.json.JSONUtil;
import com.skeqi.common.utils.JsonUtils;
import io.undertow.servlet.spec.HttpSessionImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 日志拦截器
 */
@Slf4j
//@Component
//@Aspect
public class LogInterceptor {

//	@Pointcut("execution(* com.skeqi.*.controller..*.*(..))")
	public void excudeService(){}

//	@Around("excudeService()")
	public Object handleControllerMethod(ProceedingJoinPoint thisJoinPoint) throws Throwable {
		StringBuilder sb = new StringBuilder(1000);
		long startTime = System.currentTimeMillis();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Signature signature = thisJoinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature)signature;
		Method targetMethod = methodSignature.getMethod();
		Object[] objects = thisJoinPoint.getArgs();
		Object[] param = new Object[objects.length];
		Integer step=0;
		for (int i = 0; i < objects.length; i++) {
			if (objects[i] instanceof ServletRequest || objects[i] instanceof ServletResponse || objects[i] instanceof MultipartFile || objects[i] instanceof HttpSessionImpl) {
				continue;
			}
			param[step]=objects[i];
			step++;
		}
		sb.append("----------------request start----------------\n");
		sb.append("Controller: ").append(targetMethod.getDeclaringClass().getName()).append("\n");
		sb.append("Method    : ").append(targetMethod.getName()).append("\n");
		sb.append("Params    : ").append(JsonUtils.toJsonString(param)).append("\n");
		sb.append("URI       : ").append(request.getRequestURI()).append("\n");
		sb.append("URL       : ").append(request.getRequestURL()).append("\n");
		System.out.println(sb);
		//获取返回对象
		Object object = thisJoinPoint.proceed();
		sb = new StringBuilder(1000);
		long endTime = System.currentTimeMillis();
		sb.append("\nReturn    : [").append(JSONUtil.toJsonStr(object)).append("]\n");
		sb.append(String.format("%s[%s ms]","耗时：",endTime-startTime)).append("]\n");
		sb.append("--------------request end-----------------\n");
		System.out.println(sb);
		return object;
	}
}
