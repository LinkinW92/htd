package com.skeqi.htd.config;

import com.skeqi.htd.common.BizException;
import com.skeqi.htd.common.Result;
import com.skeqi.htd.common.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常消息处理类
 *
 * @author linkin
 */
@RestControllerAdvice(basePackages = "com.skeqi.htd")
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class HtdGlobalExceptionHandler {
	@ExceptionHandler(value = Exception.class)
	public Result<String> handleException(Exception e) {
		log.warn("Unexpected exception happened, e:{}", e);
		return Result.fail(ResultCode.SYSTEM_DEFECT);
	}

	@ExceptionHandler(value = BizException.class)
	public Result<String> handleBizException(BizException e) {
		return Result.fail(e.getMessage());
	}
}
