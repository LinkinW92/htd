package com.skeqi.mes.util.aop;

import java.lang.annotation.*;

/**
 * 操作日志
 * module: 一级模块
 * module2: 二级模块
 * method: 具体操作
 * @author SKQ
 *
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OptionalLog {
	String module()  default "";
	String module2() default "";
    String method()  default "";
}
