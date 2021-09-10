package com.skeqi.framecore.interceptor;

import com.skeqi.common.annotation.RepeatSubmit;
import com.skeqi.common.core.domain.AjaxResult;
import com.skeqi.common.utils.JsonUtils;
import com.skeqi.common.utils.ServletUtils;
import com.skeqi.common.utils.time.DateTimeConverterUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Enumeration;

/**
 * 防止重复提交拦截器
 *
 * @author skeqi
 */
@Component
public abstract class RepeatSubmitInterceptor extends HandlerInterceptorAdapter
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {

		//logInfo(request, response,handler);
        if (handler instanceof HandlerMethod)
        {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            RepeatSubmit annotation = method.getAnnotation(RepeatSubmit.class);
            if (annotation != null)
            {
                if (this.isRepeatSubmit(request))
                {
                    AjaxResult ajaxResult = AjaxResult.error("不允许重复提交，请稍后再试");
                    ServletUtils.renderString(response, JsonUtils.toJsonString(ajaxResult));
                    return false;
                }
            }
            return true;
        }
        else
        {
            return super.preHandle(request, response, handler);
        }
    }

    /**
     * 验证是否重复提交由子类实现具体的防重复提交的规则
     *
     * @param request
     * @return
     * @throws Exception
     */
    public abstract boolean isRepeatSubmit(HttpServletRequest request);

	public void logInfo(HttpServletRequest request, HttpServletResponse response, Object handler){
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		if (handler instanceof HandlerMethod) {
			StringBuilder sb = new StringBuilder(1000);
			StringBuilder param = new StringBuilder(1000);

			sb.append("-----------------------").append(DateTimeConverterUtil.toLocalDateTime(new Date()))
				.append("-------------------------------------\n");
			HandlerMethod h = (HandlerMethod) handler;
			Enumeration<String> paramNames = request.getParameterNames();
			boolean hasParam = false;
			while (paramNames.hasMoreElements()) {
				hasParam = true;
				String paramName = (String) paramNames.nextElement();
				String value = request.getParameter(paramName);
				param.append(paramName).append(":").append(value).append(",");
			}
			if (!hasParam) {
				param.append("无");
			}
			sb.append("Controller: ").append(h.getBean().getClass().getName()).append("\n");
			sb.append("Method    : ").append(h.getMethod().getName()).append("\n");
			sb.append("Params    : ").append(param).append("\n");
			sb.append("Url       : ").append(request.getRequestURI()).append("\n");
			sb.append("请求类型       : ").append(request.getContentType()).append("\n");
			System.out.println(sb.toString());
		}
	}
}
