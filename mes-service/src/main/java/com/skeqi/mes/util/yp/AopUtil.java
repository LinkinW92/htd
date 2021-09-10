package com.skeqi.mes.util.yp;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import com.skeqi.mes.service.alarm.CAlarmUserTokenService;

@Aspect
public class AopUtil {

	@Autowired
	CAlarmUserTokenService service;

	@Autowired
	HttpServletRequest request;

	@Pointcut("execution(* com.skeqi.mes.controller.alarm.*.*(..))")
	public void token() {

	}

	@Before("token()")
	public void TokenBefore() {
//		System.out.println("验证token");
//		String token = request.getHeader("Authorization");
//		CMesUserT user = service.findUserByToken(token);
//		if (user == null) {
//			throw new RuntimeException("token无效");
//		}

	}

}
