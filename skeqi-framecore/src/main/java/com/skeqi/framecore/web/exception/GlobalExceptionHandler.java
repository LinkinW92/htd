package com.skeqi.framecore.web.exception;

import cn.hutool.core.lang.Validator;
import cn.hutool.http.HttpStatus;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.exception.BaseException;
import com.skeqi.common.exception.CustomException;
import com.skeqi.common.exception.DemoModeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;

/**
 * 全局异常处理器
 *
 * @author skeqi
 */
@RestControllerAdvice
public class GlobalExceptionHandler
{
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 基础异常
     */
    @ExceptionHandler(BaseException.class)
    public AjaxResult baseException(BaseException e)
    {
        return AjaxResult.error(e.getMessage());
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(CustomException.class)
    public AjaxResult businessException(CustomException e)
    {
        if (Validator.isNull(e.getCode()))
        {
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public AjaxResult handlerNoFoundException(Exception e)
    {
        log.error(e.getMessage(), e);
        return AjaxResult.error(HttpStatus.HTTP_NOT_FOUND, "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public AjaxResult handleAuthorizationException(AccessDeniedException e)
    {
        log.error(e.getMessage());
        return AjaxResult.error(HttpStatus.HTTP_FORBIDDEN, "没有权限，请联系管理员授权");
    }

    @ExceptionHandler(AccountExpiredException.class)
    public AjaxResult handleAccountExpiredException(AccountExpiredException e)
    {
        log.error(e.getMessage(), e);
        return AjaxResult.error(e.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public AjaxResult handleUsernameNotFoundException(UsernameNotFoundException e)
    {
        log.error(e.getMessage(), e);
        return AjaxResult.error(e.getMessage());
    }

	/**
	 * sql验证异常 唯一键冲突
	 */
	@ExceptionHandler(DuplicateKeyException.class)
	public AjaxResult validatedSqlKeyException(DuplicateKeyException e)
	{

		if(e.getCause().getMessage().contains("f_number")){
			log.error(e.getMessage());
			return AjaxResult.error(1000,"编码重复");
		}else if(e.getCause().getMessage().contains("dept_code")){
			return AjaxResult.error(1000,"机构编码重复");
		}else{
			log.error(e.getMessage(), e);
		}
		return AjaxResult.error(e.getCause().getMessage());
	}

	/**
	 * sql验证异常 字段太长
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	public AjaxResult validatedSqlIntegrityViolationException(DataIntegrityViolationException e)
	{
		log.error(e.getMessage(), e);
		return AjaxResult.error(e.getCause().getMessage());
	}


	/**
	 * 请求异常
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public AjaxResult validatedHttpMessageException(HttpMessageNotReadableException e)
	{
		if(!e.getMessage().contains("Required request body is missing")){
			log.error(e.getMessage(), e);
		}
		return AjaxResult.error("请求参数缺失，格式按照application/json");
	}



	@ExceptionHandler(Exception.class)
    public AjaxResult handleException(Exception e)
    {
        log.error(e.getMessage(), e);
        return AjaxResult.error("服务器开小差，请稍后再试！");
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public AjaxResult validatedBindException(BindException e)
    {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return AjaxResult.error(message);
    }


    /**
     * 自定义验证异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public AjaxResult constraintViolationException(ConstraintViolationException e) {
        log.error(e.getMessage(), e);
        String message = e.getConstraintViolations().iterator().next().getMessage();
        return AjaxResult.error(message);
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object validExceptionHandler(MethodArgumentNotValidException e)
    {
        log.error(e.getMessage());
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return AjaxResult.error(1000,message);
    }

    /**
     * 演示模式异常
     */
    @ExceptionHandler(DemoModeException.class)
    public AjaxResult demoModeException(DemoModeException e)
    {
        return AjaxResult.error("演示模式，不允许操作");
    }
}
